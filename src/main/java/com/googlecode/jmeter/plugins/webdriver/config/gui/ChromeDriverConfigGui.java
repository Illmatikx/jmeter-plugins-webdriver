package com.googlecode.jmeter.plugins.webdriver.config.gui;

import com.googlecode.jmeter.plugins.webdriver.config.ChromeDriverConfig;
import kg.apc.jmeter.JMeterPluginsUtils;
import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;

public class ChromeDriverConfigGui extends WebDriverConfigGui {

    private static final long serialVersionUID = 100L;
    JTextField chromeServicePath;
    JTextField chromeDownloadPath;
    JTextField chromeSourceFilePath;
    JCheckBox androidEnabled;
    private JCheckBox headlessEnabled;
    private JCheckBox insecureCertsEnabled;

    @Override
    public String getStaticLabel() {
        return JMeterPluginsUtils.prefixLabel("Chrome Driver Config");
    }

    @Override
    public String getLabelResource() {
        return getClass().getCanonicalName();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if(element instanceof ChromeDriverConfig) {
            ChromeDriverConfig config = (ChromeDriverConfig)element;
            chromeServicePath.setText(config.getChromeDriverPath());
            chromeDownloadPath.setText(config.getChromeDownloadPath());
            chromeSourceFilePath.setText(config.getchromeSourceFilePath());
            androidEnabled.setSelected(config.isAndroidEnabled());
            getHeadlessEnabled().setSelected(config.isHeadlessEnabled());
            getInsecureCertsEnabled().setSelected(config.isInsecureCertsEnabled());
        }
    }

    @Override
    public TestElement createTestElement() {
        ChromeDriverConfig element = new ChromeDriverConfig();
        modifyTestElement(element);
        return element;
    }

    @Override
    public void modifyTestElement(TestElement element) {
        super.modifyTestElement(element);
        if(element instanceof ChromeDriverConfig) {
            ChromeDriverConfig config = (ChromeDriverConfig)element;
            config.setChromeDriverPath(chromeServicePath.getText());
            config.setChromeDownloadPath(chromeDownloadPath.getText());
            config.setchromeSourceFilePath(chromeSourceFilePath.getText());
            config.setAndroidEnabled(androidEnabled.isSelected());
            config.setHeadlessEnabled(getHeadlessEnabled().isSelected());
            config.setInsecureCertsEnabled(getInsecureCertsEnabled().isSelected());
        }
    }

    @Override
    public void clearGui() {
        super.clearGui();
        chromeServicePath.setText("");
        chromeDownloadPath.setText("");
        chromeSourceFilePath.setText("");
        androidEnabled.setSelected(false);
        getHeadlessEnabled().setSelected(false);
        getInsecureCertsEnabled().setSelected(false);
    }

    @Override
    protected JPanel createBrowserPanel() {
        return createServicePanel();
    }

    @Override
    protected String browserName() {
        return "Chrome";
    }

    @Override
    protected String getWikiPage() {
        return "ChromeDriverConfig";
    }

    private JPanel createServicePanel() {
        final JPanel browserPanel = new VerticalPanel();
        final JPanel chromeServicePanel = new HorizontalPanel();
        final JLabel chromeDriverServiceLabel = new JLabel("Path to Chrome Driver");
        chromeServicePanel.add(chromeDriverServiceLabel);
        chromeServicePath = new JTextField();
        chromeServicePanel.add(chromeServicePath);
        final JLabel downloadPathLabel = new JLabel("Path to Download Directory");
        chromeServicePanel.add(downloadPathLabel);
        chromeDownloadPath = new JTextField();
        chromeServicePanel.add(chromeDownloadPath);
        browserPanel.add(chromeServicePanel);
        //source file panel
        final JPanel chromeSourceFilePanel = new HorizontalPanel();
        final JLabel sourceFilePathLabel = new JLabel("Path to Stream Source File");
        chromeSourceFilePath = new JTextField();
        chromeSourceFilePanel.add(sourceFilePathLabel);
        chromeSourceFilePanel.add(chromeSourceFilePath);
        browserPanel.add(chromeSourceFilePanel);

        androidEnabled = new JCheckBox("Use Chrome on Android");
        browserPanel.add(androidEnabled);

        headlessEnabled = new JCheckBox("Use Chrome headless mode");
        browserPanel.add(getHeadlessEnabled());

        insecureCertsEnabled = new JCheckBox("Allow Insecure Certs");
        browserPanel.add(getInsecureCertsEnabled());
        return browserPanel;
    }

	@Override
	protected boolean isProxyEnabled() {
		return true;
	}

	@Override
	protected boolean isExperimentalEnabled() {
		return true;
	}

    public JCheckBox getHeadlessEnabled() {
        return headlessEnabled;
    }
    
    public JCheckBox getInsecureCertsEnabled() {
        return insecureCertsEnabled;
    }
}
