package de.friedenhagen.pmd_private;

import java.net.URI;

/**
 * Resolves links to the Testlink instance.
 *
 * @author Mirko Friedenhagen
 */
class TestLinkUriResolver {

    /** baseUri of the Testlink instance. */
    private final URI baseUri;

    /**
     * The constructor normalizes the URI by adding a trailing slash when it is missing.
     * 
     * @param baseUri
     *            without <tt>index.php</tt>, eg <tt>http://testlink.sourceforge.net/demo/</tt>.
     */
    TestLinkUriResolver(URI baseUri) {
        final String asciiUri = baseUri.toASCIIString();
        if (asciiUri.endsWith("/")) {
            this.baseUri = baseUri;
        } else {
            this.baseUri = URI.create(asciiUri + "/");
        }
    }

    /**
     * http://testlink.sourceforge.net/demo/lib/testcases/tcPrint.php?testcase_id=2750.
     * 
     * @param internalTestLinkId
     *            id of the testcase
     * @return an URI pointing to the printview of the last version of the testcase description.
     */
    private URI fromTestLinkId(final TestLinkId.InternalTestLinkId internalTestLinkId) {
        return baseUri.resolve(String.format("lib/testcases/tcPrint.php?testcase_id=%s", internalTestLinkId.getId()));
    }

    /**
     * http://testlink.sourceforge.net/demo/lib/testcases/archiveData.php?targetTestCase=SM-1&edit=testcase&allowedit=0.
     * 
     * @param externalTestLinkId
     *            id of the testcase
     * @return an URI pointing to the printview of the last version of the testcase description.
     */
    private URI fromTestLinkId(final TestLinkId.ExternalTestLinkId externalTestLinkId) {
        return baseUri
                .resolve(String.format("lib/testcases/archiveData.php?targetTestCase=%s&edit=testcase&allowedit=0",
                        externalTestLinkId.getId()));
    }

    /**
     * Returns a link to the last version of the testcase description.
     *
     * http://testlink.sourceforge.net/demo/lib/testcases/tcPrint.php?testcase_id=2750
     * http://testlink.sourceforge.net/demo/lib/testcases/archiveData.php?targetTestCase=SM-1&edit=testcase&allowedit=0
     * 
     * @param testLinkId
     *            of the test case.
     * @return an URI pointing to the last version of the testcase description.
     */
    URI fromTestLinkId(final TestLinkId<?> testLinkId) {
        // As this class is package protected we may safely assume there only two kinds of TestLinkIds.
        if (testLinkId instanceof TestLinkId.InternalTestLinkId) {
            return fromTestLinkId((TestLinkId.InternalTestLinkId) testLinkId);
        } else {
            return fromTestLinkId((TestLinkId.ExternalTestLinkId) testLinkId);
        }
    }
}
