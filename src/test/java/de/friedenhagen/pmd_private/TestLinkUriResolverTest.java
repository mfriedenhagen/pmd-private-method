package de.friedenhagen.pmd_private;

import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class TestLinkUriResolverTest {

    final TestLinkUriResolver sut = new TestLinkUriResolver(URI.create("http://example.org"));

    @Test
    public void testFromTestLinkIdExternal() throws Exception {
        assertEquals(
                URI.create("http://example.org/lib/testcases/archiveData.php?targetTestCase=foo&edit=testcase&allowedit=0"),
                sut.fromTestLinkId(new TestLinkId.ExternalTestLinkId("foo")));
    }

    @Test
    public void testFromTestLinkIdInternal() throws Exception {
        assertEquals(
                URI.create("http://example.org/lib/testcases/tcPrint.php?testcase_id=2"),
                sut.fromTestLinkId(new TestLinkId.InternalTestLinkId(2L)));
    }
}