package src.java.modules.spaces;

import src.java.views.SpaceViewType;

/**
 * Interface of Spaces with common public functions.
 */
public interface ISpace {
    SpaceViewType getType();

    boolean hasVendor();
}
