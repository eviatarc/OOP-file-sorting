package filesprocessing;

import java.io.File;

/**
 * a class that create the all filter
 */
class FilterAll extends FilterTypes {

    /**
     * defualt constructor
     */
    FilterAll(){}

    @Override
    protected boolean specificCondition(File file) {
        return true;
    }
}
