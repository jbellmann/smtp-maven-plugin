/**
 * Copyright (C) 2010-2012 Joerg Bellmann <joerg.bellmann@googlemail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.jbellmann.maven.plugin.smtp;

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
        if (o == null) {
            getLog().error("The expected object was not in the plugin-context. This could be a bug.");
        } else {
            Wiser wiser = (Wiser) o;
            wiser.stop();
        }
        getLog().info("SMTP-Server stopped");
    }

}
