package de.jbellmann.smtp.maven;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import de.jbellmann.junit.smtp.subethamail.FileStorageWiser;

/**
 * 
 * @author Joerg Bellmann
 *
 * @goal run
 */
public class StartMojo extends AbstractMojo {
    
    /**
     * @parameter expression="wiser.port" default-value="2500"
     */
    private int port;
    
    /**
     * @parameter default-value="${project.build.directory}/wiserMails"
     * @readonly
     */
    private File mailStore;

    @SuppressWarnings("unchecked")
    public void execute() throws MojoExecutionException, MojoFailureException {
        createMailStore();
        final FileStorageWiser wiser = new FileStorageWiser(mailStore);
        wiser.setPort(port);
        wiser.start();
        getLog().info("SMTP-Server started on port " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                wiser.stop();
            }
        });
        getPluginContext().put(WiserConstants.WISER, wiser);
    }
    
    protected void createMailStore(){
        if(!mailStore.exists()){
            mailStore.mkdirs();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public File getMailStore() {
        return mailStore;
    }

    public void setMailStore(File mailStore) {
        this.mailStore = mailStore;
    }
}
