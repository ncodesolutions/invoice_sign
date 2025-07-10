package com.ncode.invoice.esign;

import com.ncode.invoice.pki.util.FileSigningServices;
import com.ncode.invoice.pki.util.PDFSigningServices;
import com.ncode.invoice.pki.util.PrivilagedFunctions;
import com.ncode.invoice.service.EsignService;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@EnableScheduling
@Transactional
@Component
public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	private static X509Certificate objX509Certificate = null;
	private static PrivateKey privateKeyObj;
	private static int number = 1;
	protected static int errorCode = 200;
	private static String providerName = "";
	private static java.security.cert.Certificate[] chain = null;
	public static String logFilevalue = "";
	public static String PKfilepath = null;

	private static java.security.cert.Certificate[] chain1 = null;

	@Value("${globalfileStorePath}")
	public void setGlobalfileStorePath(String path) {
		Main.PKfilepath = path;
	}

	@Autowired
	private EsignService esignService;

//	public static PrivateKey getkey() {
//		if (number == 1) {
//			logger.info("loading ks i==>" + number);
//			try {
//				String Password = "Admin12345";
//				privateKeyObj = (PrivateKey) getPrivateKey(Password);
//			} catch (Exception exception) {
//				logger.info("error", exception);
//			}
//			number = number + 1;
//		}
//		System.out.println("Private Key ::" + privateKeyObj);
//		return privateKeyObj;
//		
//	}

	public static PrivateKey getkey() {
		if (number == 1) {
			logger.info("loading ks i==>" + number);
			try {
				String Password = "Admin12345";
				privateKeyObj = (PrivateKey) getPrivateKey(Password);

				if (privateKeyObj == null) {
					throw new IllegalStateException("PrivateKey could not be loaded. Check HSM/PIN/alias.");
				}

			} catch (Exception exception) {
				logger.error("Failed to load private key", exception);
				throw new RuntimeException("Unable to load private key", exception);
			}
			number++;
		}

		System.out.println("Private Key ::" + privateKeyObj);
		return privateKeyObj;
	}

	public void startSign() {

		String[] args1 = new String[14];
		args1[0] = "PKCS11";
		args1[1] = "";
		args1[2] = "Admin12345";
		args1[3] = "\\\\192.168.40.208\\Common_Share\\Producation_Invoice_documents\\Invoice_pdf\\";
		args1[4] = "\\\\192.168.40.208\\Common_Share\\Producation_Invoice_documents\\Invoice_sign_pdf\\";
		args1[5] = "true";
		args1[6] = "500";
		args1[7] = "5";
		args1[8] = "593";
		args1[9] = "60";
		args1[10] = "A";
		args1[11] = "A";
		args1[12] = "Digitally Signed Invoice";
		args1[13] = "Ahmedabad";

		String output = "" + FileSigningServices.FAILURE;

		if (args1.length == 14) {

			Date StartDate = new Date();

			try {
				String storetype = args1[0];
				String storeFile = args1[1];
				String storePass = args1[2];
				String inFile = args1[3];
				String outFile = args1[4];
				boolean debug = Boolean.parseBoolean(args1[5]);
				int llx = Integer.parseInt(args1[6]);
				int lly = Integer.parseInt(args1[7]);
				int urx = Integer.parseInt(args1[8]);
				int ury = Integer.parseInt(args1[9]);
				String pageNo = args1[10];
				String url = args1[11];
				String reason = args1[12];
				String location = args1[13];
//				String url1 = fileStorePath ;
//				System.out.println("File Get Path" + url1);
				esignService.generatePdf();
				List<String> resultList = esignService.getAllFilePaths();

				List<String> filePathsWithStatus = new ArrayList<>();
				for (String jsonStr : resultList) {
					JSONObject obj = new JSONObject(jsonStr);
					String filepath = obj.getString("filepath");
					String status = obj.getString("status");
					filePathsWithStatus.add("Filepath: " + filepath + ", Status: " + status);
				}

				System.out.println("Filepath with Status:" + filePathsWithStatus);
				for (String line : filePathsWithStatus) {
					System.out.println(line);
					String[] parts = line.split(",\\s*"); // split into ["Filepath: ...", "Status: ..."]
					String filepath = parts[0].split("Filepath: ")[1];
					String status = parts[1].split("Status: ")[1];

					// Now you have them in separate variables
					System.out.println("Filepath Variable: " + filepath);
					System.out.println("Status Variable: " + status);

					if (status.equals("0")) {
						File tmpImFile = new File(filepath);
						if (tmpImFile.isDirectory()) {
							List<File> filesList = FileSigningServices.listAllFiles(filepath);

							System.out.println("Total files to sign ==> " + filesList.size() + "\n");

							if (filesList.size() > 0) {

								if (chain == null || chain.length == 0) {

									System.out.println("Certification Chain null " + errorCode);

								}

								for (int i = 0; i < filesList.size(); i++) {

									String outFile1 = filepath + "_Signed";
									File outfile2 = new File(outFile1);
									if (!outfile2.exists()) {
										outfile2.mkdirs();
									}
									String tmpOutFile = outFile1 + "//" + filesList.get(i).getName().substring(0,
											filesList.get(i).getName().length() - 4) + "_Signed.pdf";

									File tmpFile = new File(tmpOutFile);

									if (tmpFile.exists()) {

										System.out.println("Signed file already exists, skipping siging ==> " + i
												+ " of " + filesList.size());

										final File deleteFile = filesList.get(i);

										Boolean result1;
										try {
											deleteFile.delete();
											logger.info(
													"Trying to delete file, for which signed file exists: successful ==> "
															+ deleteFile.toString());
											System.out.println(
													"Trying to delete file, for which signed file exists: successful ==> "
															+ deleteFile.toString());
											result1 = true;
										} catch (Exception exception) {
											logger.error("File delete failed ==> " + deleteFile.toString(), exception);
											System.out.println("File delete failed ==> " + deleteFile.toString());
											result1 = false;
										}

									} else {

										int k = i + 1;
										System.out.println("Signing ==> " + k + " of " + filesList.size());

										System.out.println("Signing File ==> " + filesList.get(i));

										if (filesList.get(i).isFile()) {

											if (filesList.get(i).exists()) {

												String invoiceNo = String.valueOf(filesList.get(i).getName()
														.substring(0, filesList.get(i).getName().lastIndexOf(".")));
												System.out.println("invoiceNo" + invoiceNo);

												PrivateKey KeyObj = getkey();

												output = "" + PDFSigningServices.signPDF(KeyObj, chain, providerName,
														filesList.get(i).toString(), tmpFile.getPath(), debug, llx, lly,
														urx, ury, pageNo, url, reason, location);

												if (output.trim().equalsIgnoreCase("200")) {
//													Boolean flag = esignService.isSignCompetedMark(invoiceNo);

//													if (flag == Boolean.TRUE) {
//														System.out.println("Is Sign Flag Updated Successfully");
//													}
													try {
														Boolean result;
														try {
															List<File> filesList1 = FileSigningServices
																	.listAllFiles(System.getProperty("java.io.tmpdir"));
															if (!filesList1.isEmpty()) {
																System.out.println(
																		"Deleting temp files: " + filesList1.size());
																for (File tempFile : filesList1) {
																	tempFile.delete();
																}
															}
															result = true;
														} catch (Exception ex) {
															logger.info("Error deleting temp files", ex);
															result = false;
														}

														System.out.println("temp file deleting result : " + result);

													} catch (Exception exception) {

														System.out.println("exception while deleting files : "
																+ exception.getMessage());

														logger.info("error", exception);

													}

													System.out.println("Signing prcess status ==> " + output);

													if (output.trim().equalsIgnoreCase("200")) {

														final File deleteFile = filesList.get(i);

														Boolean result2;
														try {
															deleteFile.delete();
															logger.info("\nFile is deleted successfully ==> "
																	+ deleteFile.toString());
															System.out.println("File is deleted successfully ==> "
																	+ deleteFile.toString());
															result2 = true;
														} catch (Exception exception) {

															logger.info("\nFile deleted failed ==> "
																	+ deleteFile.toString());
															System.out.println(
																	"File deleted failed ==> " + deleteFile.toString());
															logger.info("error", exception);
															System.out.println(exception.getMessage());
															result2 = false;
														}
													} else {

														System.out.println("PDF file signing failed with name : "
																+ tmpFile.getName() + " error code : " + output
																+ " \n");
													}
												}
											}
										}
									}
								}

								System.out.println(
										"========================= PDF FILE SIGNING COMPLETED : CLOSING APPLICATION ================================\n");

							} else {

								System.out.println(
										"========================= PDF FILE SIGNING COMPLETED : NO FILES TO SIGN ================================\n");

							}
						}
					}
				}

			} catch (Exception exception) {

				System.out.println("exception.getMessage() ==> " + exception.getMessage());

				logger.info("error", exception);

			}

		}
	}

	public void mainstart() {
		ScheduledTasks scheduledTasks = new ScheduledTasks();
		scheduledTasks.runEveryTwoMinutes();

	}

	private static Key getPrivateKey(String storePass) throws Exception {
		System.out.println("Using PIN (storePass): [" + storePass + "]");

		Key privateKey = null;
		String certAlias = null;

		File file = new File(PKfilepath);
		String configPath = file.toString();

		Provider baseProvider = Security.getProvider("SunPKCS11");
		Provider pkcs11Provider = baseProvider.configure(configPath);

		System.out.println("pkcs11Provider " + pkcs11Provider);

		Security.addProvider(pkcs11Provider);

		System.out.println("=== Algorithms supported by nCipher (SunPKCS11) provider ===");
		for (Provider.Service service : pkcs11Provider.getServices()) {
			System.out.println(service.getType() + ": " + service.getAlgorithm());
		}
		System.out.println("=============================================================");

//		Provider[] providers = Security.getProviders();
//        System.out.println("=== All Registered Security Providers ===");
//        for (Provider provider : providers) {
//            System.out.println("Name: " + provider.getName());
//            System.out.println("  Info: " + provider.getInfo());
//            System.out.println("  Version: " + provider.getVersionStr());
//            System.out.println("----------------------------------------");
//        }

		KeyStore ks = KeyStore.getInstance("PKCS11", pkcs11Provider);

		System.out.println("KS ::  " + ks.toString());

		providerName = ks.getProvider().getName();

		System.out.println("provider Name " + providerName);

		ks.load(null, storePass.trim().toCharArray());
		Enumeration certificateAliases = null;
		try {
			certificateAliases = ks.aliases();

			logger.info("key length : " + ks.size());

			logger.info("certificateAliases :" + certificateAliases);
		} catch (Exception e) {
			logger.info("error", e);
		}
		if (certificateAliases.hasMoreElements()) {
			certAlias = (String) certificateAliases.nextElement();
			try {
				if (ks.isKeyEntry(certAlias)) {
					Certificate cert = ks.getCertificate(certAlias);

					chain = ks.getCertificateChain(certAlias);
					if ((cert instanceof X509Certificate)) {
						String temp = "?537d0686";
						int i = hex2Decimal(temp);
						BigInteger bigInt = BigInteger.valueOf(i);
						objX509Certificate = (X509Certificate) cert;
						logger.info("objX509Certificate.getSubjectDN() " + objX509Certificate.getSubjectDN());
						logger.info("objX509Certificate.getSerialNumber() ==> " + objX509Certificate.getSerialNumber());
						privateKey = ks.getKey(certAlias, null);
						System.out.println("Get Private Key" + privateKey);
					}
				}
			} catch (Exception e) {
				logger.info("error", e);
			}
		}

		return privateKey;
	}

//	private static Key getPrivateKey(String pass,String srNo) throws Exception 
//	{
//		String path1 = "//opt//nfast//toolkits//pkcs11//libcknfast.so";
////		String path1 = "//opt//nfast//toolkits//pkcs11//libcknfast.so";
//        String lib = "library= " + path1 + "\n";
//        String name = "name = nCipher" + "\n";
//        String sloat = "slotListIndex = 1" + "\n";
//        String attribute = "attributes = compatibility" + "\n";
//        String content = lib + name + sloat + attribute;
//        File file = PrivilagedFunctions.createTempFile(System.nanoTime() + ".properties");
////        if (debug) {
////            System.out.println("File path " + file.getPath());
////        }
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        
//        FileWriter fw = new FileWriter(file.getAbsoluteFile());
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(content);
//        bw.close();
//        byte[] keyStoreStreamBytes = null;
//        FileInputStream storeInputStream = null;
//        try {
//            storeInputStream = new FileInputStream(file);
//            keyStoreStreamBytes = new byte[storeInputStream.available()];
//            storeInputStream.read(keyStoreStreamBytes);
//            storeInputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        
//        ByteArrayInputStream keyStoreByteArrayStream = null;
//        keyStoreByteArrayStream = new ByteArrayInputStream(keyStoreStreamBytes);
//        Class<?> objProviderClass = Class.forName("sun.security.pkcs11.SunPKCS11");
//        Constructor<?> objConstructor = objProviderClass.getConstructor(InputStream.class);
//        Object[] initArgs = new Object[]{keyStoreByteArrayStream};
//        AuthProvider authProvider = (AuthProvider) objConstructor.newInstance(initArgs);
//        Security.addProvider(authProvider);
//        Enumeration<String> certificateAliases = null;
//        KeyStore ks = KeyStore.getInstance("PKCS11");
//        providerName = ks.getProvider().getName();
//        ks.load(null,pass.toCharArray());
//        
//        try {
//            certificateAliases = ks.aliases();
////            if (debug) {
//                System.out.println("key length : " + ks.size());
//                System.out.println("certificateAliases :" + certificateAliases);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//           return null;
//        }
//        Key privateKey = null;
//        
//        String certAlias = null;
//        String certAlias1 = null;
//        if (certificateAliases.hasMoreElements()) {
//            do {
//                certAlias = (String) certificateAliases.nextElement();
//                try {
//                    if (ks.isKeyEntry(certAlias)) {
//                        Certificate cert = ks.getCertificate(certAlias);
//                        chain1 = ks.getCertificateChain(certAlias);
//                        if (cert instanceof X509Certificate) {
//                            objX509Certificate = (X509Certificate) cert;
//                        }
//                            
//                            if (certAlias1.equals(srNo)) {
//                                System.out.println("input cert serail number and  found cert serial number matched ==> " + srNo.trim().equals(certAlias1.trim()));
//
//                                privateKey = ks.getKey(certAlias, null);
//                                if (privateKey != null) {
//                                    System.out.println("privateKey.getAlgorithm()" + privateKey.getAlgorithm());
//                                    System.out.println("privateKey.getFormat " + privateKey.getFormat());
//                                    break;
//                                } else {
//                                    System.out.println("Private key retival issue for cert serial number ==> " + certAlias1);
//                                }
//                            } else {
//                                System.out.println("input cert serail number ==> " + certAlias1 + " and found cert serial number ==> " + srNo + " matched ==> " + srNo.trim().equals(certAlias1.trim()));
//                            }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//
//            } while (certificateAliases.hasMoreElements());
//        }
//        file.deleteOnExit();
//        return privateKey;
//	}

	public static int hex2Decimal(String s) {
		String digits = "0123456789ABCDEF";

		s = s.toUpperCase();

		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			int d = digits.indexOf(c);

			val = 16 * val + d;
		}
		return val;
	}

}
