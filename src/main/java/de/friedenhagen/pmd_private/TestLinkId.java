package de.friedenhagen.pmd_private;;

import org.junit.runner.Description;

/**
 * Holder object for the id in the {@link TestLink} annotation. By defining only a private constructor in this abstract
 * class and having both extending classes as inner classes, we assure nobody is able to create other extending classes.
 * 
 * @param <T>
 *            of the id, either {@link Long} for internal or {@link String} for external IDs.
 *
 * @author Mirko Friedenhagen
 */
abstract class TestLinkId<T> {

    /** id of the test. */
    private final T id;

    /**
     * Private to make sure we only have {@link ExternalTestLinkId} and {@link InternalTestLinkId} as subclasses.
     *
     * @param id
     *            of the test.
     */
    private TestLinkId(final T id) {
        this.id = id;
    }

    /**
     * @return the id.
     */
    public T getId() {
        return id;
    }

    /**
     * Returns a String representation of the type of the current TestLink ID.
     *
     * @return type of the ID.
     */
    public abstract String getType();

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("%s='%s'", getType(), getId());
    }

    /**
     * An external Testlink ID.
     */
    public static class ExternalTestLinkId extends TestLinkId<String> {

        /**
         * @param id
         *            of the testcase
         */
        public ExternalTestLinkId(String id) {
            super(id);
        }

        /** {@inheritDoc} */
        @Override
        public String getType() {
            return "external_id";
        }
    }

    /**
     * An internal Testlink ID.
     */
    public static class InternalTestLinkId extends TestLinkId<Long> {

        /**
         * @param id
         *            of the testcase
         */
        public InternalTestLinkId(final Long id) {
            super(id);
        }

        /** {@inheritDoc} */
        @Override
        public String getType() {
            return "id";
        }
    }
}
