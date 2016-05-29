/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.jboss.tools.docker.ui.bot.test.ui;

import static org.junit.Assert.assertTrue;

import org.jboss.reddeer.core.exception.CoreLayerException;
import org.jboss.reddeer.eclipse.ui.console.ConsoleView;
import org.jboss.tools.docker.ui.bot.test.AbstractDockerBotTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jkopriva
 *
 */

public class PullImageTest extends AbstractDockerBotTest {
	private String imageName = "";
	
	@Before
	public void before() {
		openDockerPerspective();
		createConnection();
		this.imageName=System.getProperty("imageName");
	}
	
	
	@Test
	public void PullImageTest() {
		ConsoleView cview = new ConsoleView();
		cview.open();
		try {
			cview.clearConsole();
		} catch (CoreLayerException ex) {
			// there's not clear console button, since nothing run before
		}
		pullImage(System.getProperty("dockerServerURI"),this.imageName);
		assertTrue("Image has not been deployed!", imageIsDeployed(this.imageName));
	}
	
	@After
	public void after() {
		deleteImage(this.imageName);
		deleteConnection();
	}

	

}
