package com.example.springboot.utils;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Owen on 2018/3/23
 */
public class SftpUtil {

    /**
     * 密码连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static ChannelSftp connect(String host, int port, String username,
                                      String password) {
        ChannelSftp sftp = null;
        Session sshSession = null;
        try {
            JSch jsch = new JSch();
            if (port > 0) {
                sshSession = jsch.getSession(username, host, port);
            } else {
                sshSession = jsch.getSession(username, host);
            }
            if (sshSession == null) {
                throw new RuntimeException("session is null");
            }
            System.out.println("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.setTimeout(20000);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + ".");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sftp;
    }

    /**
     * 密钥连接sftp服务器
     *
     * @param host       主机
     * @param port       端口
     * @param userName   用户名
     * @param priKeyFile 密钥文件
     * @param passphrase 密码
     * @return
     */
    public static ChannelSftp connectByPrikey(String host, int port, String userName,
                                              String priKeyFile, String passphrase) {
        Session sshSession = null;
        JSch jsch = new JSch();
        try {
            if (priKeyFile != null && !"".equals(priKeyFile)) {
                if (passphrase != null && !"".equals(passphrase)) {
                    jsch.addIdentity(priKeyFile, passphrase);
                } else {
                    jsch.addIdentity(priKeyFile);
                }
            }
            if (port > 0) {
                sshSession = jsch.getSession(userName, host, port);
            } else {
                sshSession = jsch.getSession(userName, host);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(config);
//session.setTimeout(20000);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            return (ChannelSftp) channel;
        } catch (JSchException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param fileName  要上传的文件名
     * @param sftp
     */
    public static void upload(String directory, String fileName,
                              ChannelSftp sftp, InputStream in) {
        /*
         * sftp.cd(directory); sftp.cd(dest); sftp.mkdir(dest); File file = new
         * File(uploadFile); sftp.put(new FileInputStream(file),
         * file.getName());
         */
        mkDir(directory, sftp);
        try {
            sftp.cd(directory);
            sftp.put(in, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 下载文件
     *
     * @param outFilePath  保存到目录
     * @param ftpDirectory 下载目录
     * @param downloadFile 下载的文件
     * @param sftp
     */
    public static boolean download(String outFilePath, String ftpDirectory, String downloadFile,
                                   ChannelSftp sftp) {
        boolean isSuccess;
        InputStream in = null;
        mkDir(outFilePath);
        try {
            sftp.cd(ftpDirectory);
            sftp.get(downloadFile, outFilePath);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public static void delete(String directory, String deleteFile,
                              ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public static Vector listFiles(String directory, ChannelSftp sftp)
            throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 获取目录name
     *
     * @return
     * @throws SftpException
     */
    public static List<String> buildFiles(Vector ls) throws Exception {
        if (ls != null && ls.size() >= 0) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < ls.size(); i++) {
                ChannelSftp.LsEntry f = (ChannelSftp.LsEntry) ls.get(i);
                String nm = f.getFilename();
                if (nm.equals(".") || nm.equals(".."))
                    continue;
                list.add(nm);
            }
            return list;
        }
        return null;
    }

    /**
     * 打开指定目录
     *
     * @param directory directory
     * @return 是否打开目录
     */
    public static boolean openDir(String directory, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            return true;
        } catch (SftpException e) {
            return false;
        }
    }

    /**
     * 创建指定文件夹
     *
     * @param dirName dirName
     */
    public static void mkDir(String dirName, ChannelSftp sftp) {
        String[] dirs = dirName.split("/");

        try {
            String now = sftp.pwd();
            sftp.cd("/");
            for (int i = 0; i < dirs.length; i++) {
                if (StringUtils.isNotEmpty(dirs[i])) {
                    boolean dirExists = openDir(dirs[i], sftp);
                    if (!dirExists) {
                        sftp.mkdir(dirs[i]);
                        sftp.cd(dirs[i]);
                    }
                }
            }
            sftp.cd(now);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建指定文件夹
     *
     * @param dirName dirName
     */
    public static void mkDir(String dirName) {
        if (StringUtils.isNotEmpty(dirName)) {
            File fileDir = new File(dirName);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        }
    }

}
