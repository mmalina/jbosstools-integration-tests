package org.jboss.tools.jsf.ui.bot.test.cssdialog.jbide;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.jboss.tools.jsf.ui.bot.test.JSFAutoTestCase;
import org.jboss.tools.ui.bot.ext.gen.ActionItem;
import org.jboss.tools.ui.bot.ext.helper.ContextMenuHelper;
import org.jboss.tools.ui.bot.test.WidgetVariables;

public class JBIDE3920Test extends JSFAutoTestCase{
	
	private static String CSS_FILE_NAME = "JBIDE3920"; //$NON-NLS-1$
	private static String CSS_CLASS_NAME = "cssclass"; //$NON-NLS-1$
	
	public void testJBIDE3920(){
		SWTBot innerBot = bot.viewByTitle(WidgetVariables.PACKAGE_EXPLORER).bot();
		SWTBotTree tree = innerBot.tree();
		try {
			tree.expandNode(JBT_TEST_PROJECT_NAME).expandNode("WebContent"). //$NON-NLS-1$
			  getNode(CSS_FILE_NAME+".css").doubleClick(); //$NON-NLS-1$
			bot.editorByTitle(CSS_FILE_NAME+".css").toTextEditor().setText("@CHARSET \"UTF-8\";");
		} catch (WidgetNotFoundException e) {
			tree.getTreeItem(JBT_TEST_PROJECT_NAME).select(); //$NON-NLS-1$
			open.newObject(ActionItem.NewObject.WebCSS.LABEL);  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
			bot.shell("New CSS File").activate(); //$NON-NLS-1$
			bot.textWithLabel("File name:").setText(CSS_FILE_NAME); //$NON-NLS-1$
			bot.button("Finish").click(); //$NON-NLS-1$
		}
		SWTBotEclipseEditor eclipseEditor =	bot.editorByTitle(CSS_FILE_NAME+".css").toTextEditor(); //$NON-NLS-1$
		eclipseEditor.setFocus();
		eclipseEditor.insertText(CSS_CLASS_NAME+"{"); //$NON-NLS-1$
		eclipseEditor.save();
		ContextMenuHelper.clickContextMenu(eclipseEditor, "Open CSS Dialog"); //$NON-NLS-1$
		bot.shell("CSS Class").activate(); //$NON-NLS-1$
		bot.tabItem("Text/Font").activate(); //$NON-NLS-1$
		bot.comboBoxWithLabel("Font Style:").setSelection("italic"); //$NON-NLS-1$ //$NON-NLS-2$
		bot.comboBoxWithLabel("Text Decoration:").setSelection("underline"); //$NON-NLS-1$ //$NON-NLS-2$
		bot.button("OK").click(); //$NON-NLS-1$
		bot.editorByTitle(CSS_FILE_NAME+".css").toTextEditor().close(); //$NON-NLS-1$
	}

	@Override
	protected void closeUnuseDialogs() {
		try {
			bot.shell("CSS Class").close(); //$NON-NLS-1$
		} catch (WidgetNotFoundException e) {
		}
	}

	@Override
	protected boolean isUnuseDialogOpened() {
		boolean isOpened = false;
		try {
			bot.shell("CSS Class").activate(); //$NON-NLS-1$
			isOpened = true;
		} catch (WidgetNotFoundException e) {
		}
		return isOpened;
	}

}
