package com.example.ritesh.networkinfo.Beans;

import java.util.List;

/**
 * Created by ritesh on 16/7/17.
 */

public class BeanNetworkin {

    private String id;
    private String name;
    private List<SubCategory> subCategoryList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public static class SubCategory{

        private String id;
        private String name;
        private List<SubChild> subChildList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SubChild> getSubChildList() {
            return subChildList;
        }

        public void setSubChildList(List<SubChild> subChildList) {
            this.subChildList = subChildList;
        }

        public static class SubChild{

            private String id;
            private String name;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    }
}
