package org.cuong.intro.gwt.client;

import org.cuong.testcategories.IntegrationTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(IntegrationTest.class)
@SuiteClasses({ GwtTestGWTSample.class })
public class AllGWTUnitTestSuite {

}
