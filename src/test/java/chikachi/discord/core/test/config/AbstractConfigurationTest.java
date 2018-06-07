package chikachi.discord.core.test.config;

import chikachi.discord.core.config.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public abstract class AbstractConfigurationTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    // We always start with a clean configuration
    @Before
    public void setUp() throws Exception {
        String absolutePath = folder.getRoot().getAbsolutePath();
        Configuration.onPreInit(absolutePath);
    }

    // We always clear all files we created
    @After
    public void tearDown() throws Exception {
        folder.delete();
    }
}
