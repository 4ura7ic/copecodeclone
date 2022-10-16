package ku.cs.app.services;

import ku.cs.app.models.list.DynamicCategory;

public class DefaultCategoryHardCode {
     protected DynamicCategory defaultCategory;
     public DefaultCategoryHardCode() {
         defaultCategory =  new DynamicCategory();
         setDefaultCategory();
     }

     public void setDefaultCategory() {
         defaultCategory.addCategory("ALL");
         defaultCategory.addCategory("Education");
         defaultCategory.addCategory("Environment");
         defaultCategory.addCategory("Scholarship");
         defaultCategory.addCategory("Transportation");
     }

    public DynamicCategory getDefaultCategory() {
        return defaultCategory;
    }
}
