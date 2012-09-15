package de.jbellmann.smtp.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.subethamail.wiser.Wiser;

/**
 * 
 * @author Joerg Bellmann
 * 
 * 
 * @goal stop
 */
public class StopMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Stopping SMTP-Server ...");
        Object o = getPluginContext().get(WiserConstants.WISER);
        if(o == null){
            getLog().error("The expected object was not in the plugin-context. This could be a bug.");
        }else{
            Wiser wiser = (Wiser)o;
            wiser.stop();
        }
        getLog().info("SMTP-Server stopped");
    }

}
