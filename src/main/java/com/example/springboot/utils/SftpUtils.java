package com.example.springboot.utils;

import cn.magfin.util.PathUtil;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * @ClassName: SftpUtils
 * @description:
 * @author: zhanglize
 * @time: 2021/2/23 11:40
 */
@Service
public class SftpUtils {

    private static final Logger log = LoggerFactory.getLogger(SftpUtils.class);

    public Session sshSession = null;
    public Channel channel = null;
    public ChannelSftp sftp = null;

    public Session getSshSession() {
        return sshSession;
    }

    public void setSshSession(Session sshSession) {
        this.sshSession = sshSession;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelSftp getSftp() {
        return sftp;
    }

    public void setSftp(ChannelSftp sftp) {
        this.sftp = sftp;
    }

    /**
     * 在远程服务器上创建一个文件目录
     *
     * @param createpath 服务器创建的目录信息
     * @param sftp
     */
    public void createDir(String createpath, ChannelSftp sftp) {
        try {
            String now = sftp.pwd();
            if (isDirExist(createpath, sftp)) {
                sftp.cd(createpath);
                return;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(String.valueOf(filePath), sftp)) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(createpath);
        } catch (SftpException e) {
            log.error("创建路径错误:{}", createpath, e);
        }
    }

    /**
     * 判断目录是否存在
     */
    public boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
        }
        return isDirExistFlag;
    }

    public void getFileFromSftpServer(Channel channel, String src, String localPath) {
        ChannelSftp sftp;
        try {
            sftp = (ChannelSftp) channel;
            //获取文件目录下所有的文件
            Vector vector = sftp.ls(src);
            for (Object item : vector) {
                if (item instanceof ChannelSftp.LsEntry) {
                    String fileName = ((ChannelSftp.LsEntry) item).getFilename();
                    if (fileName.equals(".") || fileName.equals("..")) {
                        continue;
                    }
                    File localpath = new File(localPath);
                    File[] fileList = localpath.listFiles();
                    if (!localpath.exists()) {
                        localpath.mkdirs();
                        localPath = localpath.getPath();
                    }
                    for (File file : fileList) {
                        if (fileName.equals(file.getName())) {
                            file.delete();
                        }
                    }
                    sftp.get(src + "/" + fileName, localPath);
                }
            }
        } catch (Exception e) {
            log.error("下载失败:", e);
        }
    }

    public void putFileToSftpServer(Channel channel, String src, String localPath) {
        List<String> list = new ArrayList();
        try {
            sftp = (ChannelSftp) channel;
            File file = new File(localPath);
            if (!file.exists()) {
                throw new RuntimeException("本地路径有误");
            }
            File[] files = file.listFiles();
            for (File item : files) {
                list.add(item.getName());
                sftp.put(localPath + File.separator + item.getName(), src);
            }
        } catch (Exception e) {
            log.error("上传失败:", e);
        }
    }

    /**
     * 密钥连接sftp服务器
     *
     * @param host
     * @param port
     * @param username
     * @return
     */
    public Channel getChannel(String host, int port, String username) {
        try {
            File file = PathUtil.devastatorPluginsFolder();
            String prvkey = file.getAbsolutePath() + File.separator + "id_rsa_cijinrongxz.pem";
            JSch jsch = new JSch();
            jsch.addIdentity(prvkey);
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            log.info("sshSession connected!");
            channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("Channel connected!");
        } catch (Exception e) {
            log.error("连接失败", e);
        }
        return channel;
    }

    /**
     * 密码连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public Channel connect(String host, int port, String username, String password) {
        log.info("获取SFTP服务器连接username:{},host:{},port:{}", username, host, port);
        try {
            JSch jsch = new JSch();
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.setTimeout(20000);
            sshSession.connect();
            channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("Connected to " + host + ".");
        } catch (Exception e) {
            log.error("密码连接sftp服务器异常：", e);
        }
        return channel;
    }

    public void closeResource(Channel channel, ChannelSftp sftp, Session session) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }


}
