package com.example.ritesh.networkinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ritesh.networkinfo.Beans.BeanNetworkin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ritesh on 16/7/17.
 */

public class NetworkingListInsideList extends AppCompatActivity {


    @BindView(R.id.rv_main_category)
    RecyclerView Rv_main_category;

    List<NetworkingListInsideList> rowMainCategoryItems;
    private ArrayList<String> cat_names;
    private ArrayList<String> cat_id;

    String Category_Id = "",
            main_cat_list = "",
            Category_Name = "";


    String MainCategory_ID = "",
            Category_Name_for_Sub_Category = "",
            StrGet_status = "",
            StrGet_message = "",
            StrGet_result = "",
            StrGet_Category_id = "",
            StrGet_Message = "",
            StrGet_result_list = "",
            StrGet_Category_name = "";


    private ProgressDialog pDialog;
//    private static final String MERCHANT_DETAIL_URL = "http://whatsapphindistatus.com/ZOF/webservice/main_category?";
    private static final String MERCHANT_DETAIL_URL = "http://whatsapphindistatus.com/ZOF/webservice/testing_catg";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);
        ButterKnife.bind(this);
        AndroidNetworking.initialize(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        rowMainCategoryItems = new ArrayList<NetworkingListInsideList>();
        cat_id = new ArrayList<>();
        cat_names = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        Rv_main_category.setLayoutManager(mLayoutManager);
//        Rv_main_category.addItemDecoration(new EqualSpaceItemDecoration(5));
        Rv_main_category.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));
//        Rv_main_category.setHasFixedSize(true);

//        CategoryAdapter categoryAdapter=new CategoryAdapter(NetworkingListInsideList.this,null);
//        Rv_main_category.setAdapter(categoryAdapter);

//        GetMerchantDetail();
        Getlist();


    }


    private void Getlist() {
        Log.e("GetMerchantDetailFast********************* :", "*************************");
        pDialog.setMessage("Please Wait Fetching Detail.....");
        showDialog();

        AndroidNetworking.post(MERCHANT_DETAIL_URL)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        hideDialog();
                        Log.e("Response GetMerchantDetailFast :", "" + response);
                        List<BeanNetworkin> beanNetworkinList=new ArrayList<BeanNetworkin>();
                        try {


                            JSONObject mainObject=new JSONObject(response.toString());
                            String message=mainObject.getString("message");
                            if (message.equalsIgnoreCase("successful")){


                                JSONArray resultArray=mainObject.getJSONArray("result");

                                for (int i=0;i<resultArray.length();i++){

                                    JSONObject categoryObject=resultArray.getJSONObject(i);

                                    BeanNetworkin beanNetworkin=new BeanNetworkin();
                                    beanNetworkin.setId(categoryObject.getString("id"));
                                    beanNetworkin.setName(categoryObject.getString("name"));



                                    List<BeanNetworkin.SubCategory> subCategoryList=new ArrayList<BeanNetworkin.SubCategory>();

                                    JSONArray subCategoryArray=categoryObject.getJSONArray("subcategory");

                                    for (int j=0;j<subCategoryArray.length();j++){

                                        JSONObject subCategoryObject=subCategoryArray.getJSONObject(j);

                                        BeanNetworkin.SubCategory subCategory=new BeanNetworkin.SubCategory();
                                        subCategory.setId(subCategoryObject.getString("id"));
                                        subCategory.setName(subCategoryObject.getString("name"));


                                     List<BeanNetworkin.SubCategory.SubChild> subChildList=new ArrayList<BeanNetworkin.SubCategory.SubChild>();

                                        JSONArray childCategoryArray=subCategoryObject.getJSONArray("sub_child");


                                        for (int k=0;k<childCategoryArray.length();k++){


                                            JSONObject childCategoryObject=childCategoryArray.getJSONObject(k);


                                            BeanNetworkin.SubCategory.SubChild subChild=new BeanNetworkin.SubCategory.SubChild();
                                            subChild.setId(childCategoryObject.getString("id"));
                                            subChild.setName(childCategoryObject.getString("name"));
                                            subChildList.add(subChild);

                                        }

                                        subCategory.setSubChildList(subChildList);
                                        subCategoryList.add(subCategory);

                                    }

                                    beanNetworkin.setSubCategoryList(subCategoryList);
                                    beanNetworkinList.add(beanNetworkin);

                                }




                            }




                            Log.e("AllVALUES",beanNetworkinList.get(0).getName());
                            Log.e("AllVALUESSUB",beanNetworkinList.get(0).getSubCategoryList().get(0).getName());
                            Log.e("AllVALUESChild",beanNetworkinList.get(0).getSubCategoryList().get(0).getSubChildList().get(0).getName());



                            hideDialog();

                        } catch (JSONException e) {
                            Log.e("Exception",e.getMessage());
                            hideDialog();
                            e.printStackTrace();
                        }


                        // after goes

                        CategoryAdapter categoryAdapter=new CategoryAdapter(NetworkingListInsideList.this,beanNetworkinList);
                        Rv_main_category.setAdapter(categoryAdapter);

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        hideDialog();
//                        Toast.makeText(LoginActivity.this, "Server error try again", Toast.LENGTH_LONG).show();
                        Log.e("Error Response :", "" + error.toString());


                    }
                });
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }





    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

        private Context mContext;
        List<BeanNetworkin> beanNetworkinList;


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView CatName;

            public MyViewHolder(View view) {
                super(view);
                CatName = (TextView) view.findViewById(R.id.gv_main_tv);

            }
        }


        public CategoryAdapter(Context mContext, List<BeanNetworkin> beanNetworkinList) {
            this.mContext = mContext;
            this.beanNetworkinList = beanNetworkinList;
        }

        @Override
        public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.business_list_items, parent, false);

            return new CategoryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CategoryAdapter.MyViewHolder holder, final int position) {

            SubCategoryAdapter subCategoryAdapter=new SubCategoryAdapter(mContext,beanNetworkinList.get(position).getSubCategoryList());
//            holder.listview.setadapter(subCategoryAdapter);


        }


        @Override
        public int getItemCount() {
            return beanNetworkinList.size();
//            return 10;
        }


    }





    private class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

        private Context mContext;
        List<BeanNetworkin.SubCategory> subCategoryList;


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView CatName;

            public MyViewHolder(View view) {
                super(view);
                CatName = (TextView) view.findViewById(R.id.gv_main_tv);

            }
        }


        public SubCategoryAdapter(Context mContext, List<BeanNetworkin.SubCategory> subCategoryList) {
            this.mContext = mContext;
            this.subCategoryList = subCategoryList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.business_list_items, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {




        }


        @Override
        public int getItemCount() {
            return subCategoryList.size();
//            return 10;
        }


    }



















}
