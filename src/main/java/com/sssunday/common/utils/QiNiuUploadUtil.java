package com.sssunday.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.BucketManager.Batch;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.sssunday.common.constant.Constant;

import net.sf.json.JSONObject;

/**
 *七牛文件上传工具类
 *
 */
public class QiNiuUploadUtil {
	//设置好账号的ACCESS_KEY和SECRET_KEY
	private static final	Auth auth = Auth.create(Constant.QiNiuFileCfg.ACCESS_KEY, Constant.QiNiuFileCfg.SECRET_KEY);//密钥配置
	private static final int BUFFER_SIZE = 8192;
	
	/**
	 * 获取指定的uptoken
	 */
	public static String getUpToken(String bucketname){
		return auth.uploadToken(bucketname);
	}
	/**
	 * 获取默认的uptoken
	 */
	public static String getUpToken(){
		return getUpToken(Constant.QiNiuFileCfg.BUCKE_DEFAULT);
	}
	/**
	 * 创建上传对象
	 */
	private static UploadManager getUploadManager(){
		return new UploadManager();
	}
	
	/**
	 * 创建BucketManager对象
	 */
	private static BucketManager getBucketManager(){
		return new BucketManager(auth);
	}
	/**
	 * 上传文件-文件路径
	 * @param filePath 文件绝对路径
	 * @param bucke 存储位置
	 * @return
	 */
	public static String upload(String filePath,String bucke){
		File file=null;
		try {
				file = new File(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return upload(file,bucke);
	}
	/**
	 * 上传文件-输入流
	 * @param is 输入流
	 * @param bucke 存储位置
	 * @return
	 */
	public static String upload(InputStream is,String fileName,String bucke){
		File file=null;
		try {
				file = saveFileByInputStream(is,fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return upload(file,bucke);
	}
	/**
	 * 上传文件-文件
	 * @param file 输入文件
	 * @param bucke 存储位置
	 * @return
	 */
	public static String upload(File file,String bucke) {
		String token = null;
		String name = null;
		try {
			if(file.isDirectory() || !file.exists()){throw new Exception("上传的文件不存在！");}
			if(bucke != null)
				token = getUpToken(bucke);
			else
				token = getUpToken();
			Response res = getUploadManager().put(file, file.getName(), token);
			name = (String) res.jsonToMap().get("key");
			System.out.println("上传七牛成功信息："+res.bodyString());
		} catch (QiniuException e) {
			 Response res = e.response;
	         try {
	        	// 请求失败时打印的异常的信息
	        	 if(res != null)
	        		 System.out.println("上传七牛失败信息："+res.bodyString());
	        	 else
	        		 e.printStackTrace();
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * 删除文件
	 * @param file
	 * @param bucke
	 * @return
	 */
	public static boolean delete(String bucket,String key) {
		boolean msg = false;
		try {
			BucketManager bucketManager = new BucketManager(auth);
			//调用delete方法移动文件
		    bucketManager.delete(bucket, key);
		    msg = true;
			System.out.println("七牛删除图片成功!");
		} catch (QiniuException e) {
	        	// 请求失败时打印的异常的信息
				 System.out.println("七牛删除图片失败信息："+e.response);
		}
		return msg;
	}
	
	/**
	 * 批量删除文件
	 * @param file
	 * @param bucke
	 * @return
	 */
	public static boolean delete(String bucket,String[] key) {
		boolean msg = false;
		try {
			BucketManager bucketManager = new BucketManager(auth);
			BucketManager.Batch bucketManagerBatch = new BucketManager.Batch();
			Batch batch = bucketManagerBatch.delete(bucket, key);
			Response res = bucketManager.batch(batch);
		    msg = true;
			System.out.println("七牛批量删除图片成功信息："+res.bodyString());
		} catch (QiniuException e) {
	        	// 请求失败时打印的异常的信息
				 System.out.println("七牛删除图片失败信息："+e.response);
		}
		return msg;
	}
	
	
	/**
	 * 获取文件地址
	 */
	public static String getFileUrl(String name){
		return Constant.QiNiuFileCfg.FILESERVER_URL+name;
	}
	/**
	 * 随机生成文件名
	 * @param originFileName
	 * @return
	 */
	public static String generatorFileName(String originFileName){
		String suffix ="";
		if(originFileName != null){
			suffix = originFileName.substring( originFileName.lastIndexOf( "." ) ).toLowerCase();
		}
		String name = (Math.random() * 10000 + "").replace(".", "");
		return name+suffix;
	}
	/**
	 * 创建临时文件
	 */
	public static File generatorFile(String fileName){
		File tmpDir = FileUtils.getTempDirectory();
		//String tmpFileName = generatorFileName(originFileName);
		return new File(tmpDir, fileName);
	}
	
	/**
	 * 获取文件信息
	 */
	public static JSONObject getFileInfo(String bucket,String key){
		JSONObject obj = null;
		try {
			//调用stat()方法获取文件的信息
		      FileInfo info = getBucketManager().stat(bucket, key);
		      obj = JSONObject.fromObject(info);
		      System.out.println("查询文件信息："+obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return obj;
	}
	
	private static File saveFileByInputStream(InputStream is, String fileName){
		File tmpFile = generatorFile(fileName);
		byte[] dataBuf = new byte[ 2048 ];
		BufferedInputStream bis = new BufferedInputStream(is, QiNiuUploadUtil.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(tmpFile), QiNiuUploadUtil.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return tmpFile;
	}
}
