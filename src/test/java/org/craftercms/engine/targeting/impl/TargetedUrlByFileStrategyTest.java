package org.craftercms.engine.targeting.impl;

import java.util.Arrays;
import java.util.List;

import org.craftercms.engine.targeting.TargetIdManager;
import org.craftercms.engine.targeting.TargetedUrlComponents;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TargetedUrlByFileStrategy}.
 *
 * @author avasquez
 */
public class TargetedUrlByFileStrategyTest {

    private static final List<String> AVAILABLE_TARGET_IDS = Arrays.asList("ame", "ame_lat", "ame_lat_cr");
    private static final String DEFAULT_TARGET_ID = "ame";
    private static final String CURRENT_TARGET_ID = "ame_lat_cr";
    private static final String NON_TARGETED_URL = "/products/index.xml";
    private static final String TARGETED_URL1 = "/products/index_ame_lat_cr.xml";
    private static final String TARGETED_URL2 = NON_TARGETED_URL;

    private TargetedUrlByFileStrategy targetedUrlStrategy;
    @Mock
    private TargetIdManager targetIdManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(targetIdManager.getFallbackTargetId()).thenReturn(DEFAULT_TARGET_ID);
        when(targetIdManager.getCurrentTargetId()).thenReturn(CURRENT_TARGET_ID);
        when(targetIdManager.getAvailableTargetIds()).thenReturn(AVAILABLE_TARGET_IDS);

        targetedUrlStrategy = new TargetedUrlByFileStrategy();
        targetedUrlStrategy.setTargetIdManager(targetIdManager);
    }

    @Test
    public void testToTargetUrl() throws Exception {
        String targetedUrl = targetedUrlStrategy.toTargetedUrl(NON_TARGETED_URL, false);

        assertEquals(TARGETED_URL1, targetedUrl);
    }

    @Test
    public void testToTargetUrlWithNoCurrentTargetId() throws Exception {
        when(targetIdManager.getCurrentTargetId()).thenReturn(null);

        String targetedUrl = targetedUrlStrategy.toTargetedUrl(NON_TARGETED_URL, false);

        assertEquals(TARGETED_URL2, targetedUrl);
    }

    @Test
    public void testParseUrl() throws Exception {
        TargetedUrlComponents urlComp = targetedUrlStrategy.parseTargetedUrl(TARGETED_URL1);

        assertEquals("/products/index", urlComp.getPrefix());
        assertEquals("ame_lat_cr", urlComp.getTargetId());
        assertEquals(".xml", urlComp.getSuffix());
    }

    @Test
    public void testBuildUrl() throws Exception {
        String targetedUrl = targetedUrlStrategy.buildTargetedUrl("/products/index", "ame_lat_cr", ".xml");

        assertEquals(TARGETED_URL1, targetedUrl);
    }

}