package com.example.ritesh.networkinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class Networking extends AppCompatActivity {


    @BindView(R.id.rv_main_category)
    RecyclerView Rv_main_category;

    List<Networking> rowMainCategoryItems;
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
    private static final String MERCHANT_DETAIL_URL = "http://whatsapphindistatus.com/ZOF/webservice/main_category?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);
        ButterKnife.bind(this);
        AndroidNetworking.initialize(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        rowMainCategoryItems = new ArrayList<Networking>();
        cat_id = new ArrayList<>();
        cat_names = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        Rv_main_category.setLayoutManager(mLayoutManager);
//        Rv_main_category.addItemDecoration(new EqualSpaceItemDecoration(5));
        Rv_main_category.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));
//        Rv_main_category.setHasFixedSize(true);

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
                            JSONObject jobjresponse = new JSONObject(String.valueOf(response));
                            Log.e("Get List Detail :", "" + jobjresponse);

                            main_cat_list = jobjresponse.getString("result");
                            Log.e("************Json main_cat_list data*******************", " " + main_cat_list);


//                            if (StrGet_Merc_status.equalsIgnoreCase("1")) {
//                                Log.e("StrGet_Merc_status jobjresponse is:", "1");
//                                hideDialog();
//                            }


                            JSONArray jaaray = new JSONArray(main_cat_list);

                            for (int i = 0; i < jaaray.length(); i++) {
                                StrGet_result_list = jaaray.getJSONObject(i).getString("result");
                                if (StrGet_result_list.equalsIgnoreCase("success")) {
                                    BeanNetworkin beanNetworkin = new BeanNetworkin();
//                                    BeanNetworkin.setId(jaaray.getJSONObject(i).getString("id"));
//                                    BeanNetworkin.setUsername(jaaray.getJSONObject(i).getString("name"));
////                                    beanMainCategory0.add(beanMainCategory);

                                    beanNetworkin.setId(jaaray.getJSONObject(i).getString("id"));
                                    beanNetworkin.setUsername(jaaray.getJSONObject(i).getString("name"));
//                                    beanNetworkins0.add(beanNetworkin);
                                    beanNetworkinList.add(beanNetworkin);


                                    Category_Id = jaaray.getJSONObject(i).getString("id");
                                    Category_Name = jaaray.getJSONObject(i).getString("name");

                                    cat_id.add(Category_Id);
                                    cat_names.add(Category_Name);
                                    Log.e(" ********** SellerProduct_id **********", "" + cat_id);
                                    Log.e(" ********** SellerProduct_names **********", "" + cat_names);


                                    String cat_idlist = cat_id.get(i);
                                    Log.e(" ********** cat_idlist **********", "" + cat_idlist);

                                    String cat_nameslist = cat_names.get(i);
                                    Log.e(" ********** cat_nameslist **********", "" + cat_nameslist);
                                }
                            }


                            hideDialog();

                        } catch (JSONException e) {
                            hideDialog();
                            e.printStackTrace();
                        }


                        // after goes

                        CategoryAdapter categoryAdapter=new CategoryAdapter(Networking.this,beanNetworkinList);
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
        private List<BeanNetworkin> arrayList;


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView CatName;

            public MyViewHolder(View view) {
                super(view);
                CatName = (TextView) view.findViewById(R.id.gv_main_tv);

            }
        }


        public CategoryAdapter(Context mContext, List<BeanNetworkin> beanNetworkinList) {
            this.mContext = mContext;
            this.arrayList = beanNetworkinList;
        }

        @Override
        public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.business_list_items, parent, false);

            return new CategoryAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CategoryAdapter.MyViewHolder holder, final int position) {

            holder.CatName.setText(Html.fromHtml(arrayList.get(position).getCatname()));


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();

//                    holder.Catimage_transparent_overlay.setVisibility(View.GONE);
                    Log.e(" Item Position :", "" + arrayList.get(holder.getLayoutPosition()));

                    Log.e(" Adapter Position :", "" + position);

                    Log.e("Main Categery id", "" + arrayList.get(position).getId());
                    MainCategory_ID = arrayList.get(position).getId();
                    Log.e("MainCategory_ID :", "" + MainCategory_ID);
                    Category_Name_for_Sub_Category = String.valueOf(Html.fromHtml(arrayList.get(position).getCatname()));

                    Log.e("Item Name", "" + String.valueOf(Html.fromHtml(arrayList.get(position).getCatname())));

                    Log.e("Item Name Category_Name_for_Sub_Category", "" + Category_Name_for_Sub_Category);

                    Log.e(" List Size :", "" + arrayList.size());

//                    Intent SubCatPage = new Intent(getApplicationContext(), SubCategoryActivity.class);
//                    SubCatPage.putExtra("SubCatID", arrayList.get(position).getId());
//                    SubCatPage.putExtra("MainCatID", MainCategory_ID);
//                    SubCatPage.putExtra("SubCatName", Category_Name_for_Sub_Category);
////                    SubCatPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    MainActivity.this.startActivity(SubCatPage);
//                    finish();

                }
            });


        }


        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }








}
