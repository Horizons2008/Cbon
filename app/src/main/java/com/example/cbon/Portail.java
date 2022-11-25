package com.example.cbon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.cbon.fragment.FragCommande;
import com.example.cbon.fragment.FragHome;
import com.example.cbon.fragment.FragNotif;
import com.example.cbon.fragment.FragProfile;

import org.json.JSONObject;

public class Portail extends AppCompatActivity {

    Toolbar toolbar;
    MeowBottomNavigation BottomNav;
    Fragment frg ;



   void  initIHM(){
       toolbar=findViewById(R.id.Toolbar);
       BottomNav=findViewById(R.id.BottomNav);


   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Toast.makeText(Portail.this, "expanded", Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Toast.makeText(Portail.this, "collipsed", Toast.LENGTH_SHORT).show();
                return true;
            }


        };
        menu.findItem(R.id.bt_search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView=(SearchView) menu.findItem(R.id.bt_search).getActionView();
        searchView.setBackgroundColor(Color.rgb(255,255,255));
        searchView.setBackground(getDrawable(R.drawable.bg_search));

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portail);
        initIHM();
        BottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.bot_nav_home_24));
        BottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.bot_nav_category_24));
        BottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.bot_nav_notifications_24));


        BottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {


                if (item.getId()==1) {frg=new FragHome(); }
                if (item.getId()==2) {frg=new FragCommande(); }
                if (item.getId()==3) {frg=new FragProfile(); }


                getSupportFragmentManager().beginTransaction().replace(R.id.Frag,frg,null).commit();
            }
        });
        BottomNav.show(1,true);
        BottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {





            }


        });

        BottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(Portail.this, "77777", Toast.LENGTH_SHORT).show();


            }
        });


        setSupportActionBar(toolbar);
        getCategories();
    }// on create

    void getCategories()
    {
        {
           // findViewById(R.id.progress_dossier).setVisibility(View.VISIBLE);
            JSONObject request = new JSONObject();
            try {
              //  request.put("lngUser", lng.getLng());
              //  request.put("wilaya_id", lng.getWilaya_id());
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*Log.e("TAGS", request.toString());
        Log.e("TAGS", CommunConfigAndFonction.url + "portail/getPortail");*/
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                   // CommunFunction.url + "portail/getPortail/v4",
                    "https://restfull-client.cbon.ml/api/portail/getPortail",
                    request,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("TAGS", response.toString());
                           // findViewById(R.id.progress_dossier).setVisibility(GONE);
                          //  ObjectMapper mapper = new ObjectMapper();
                            try {
                              /*  PortailVo portailVo = mapper.readValue(response.toString(), PortailVo.class);
                                categorieAdapter = new CategorieAdapter(getApplicationContext(), portailVo.getListC(), PortailActivity.this, lng.getLng());
                                list_categorie.setAdapter(categorieAdapter);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(PortailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                list_categorie.setLayoutManager(layoutManager);

//                            articleRecyclerViewAdapterReduction = new ArticleRecyclerViewAdapter(PortailActivity.this, portailVo.getArticlesReduction(), PortailActivity.this, portailVo.getTypeMony(), 1);
                                articleRecyclerViewAdapterReduction = new ArticleRecyclerViewAdapter(PortailActivity.this, portailVo.getArticlesReduction(), PortailActivity.this, lng.getLng(), 1);


                                recycler_article_reduction.setAdapter(articleRecyclerViewAdapterReduction);
                                LinearLayoutManager layoutManager2 = new LinearLayoutManager(PortailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                recycler_article_reduction.setLayoutManager(layoutManager2);
                                if (portailVo.getArticlesReduction().isEmpty())
                                    findViewById(R.id.layout_reduction).setVisibility(GONE);
                                else
                                    findViewById(R.id.layout_reduction).setVisibility(View.VISIBLE);

//                            articleRecyclerViewAdapterNew = new ArticleRecyclerViewAdapter(PortailActivity.this, portailVo.getArticlesNouveau(), PortailActivity.this, portailVo.getTypeMony(), 2);
                                articleRecyclerViewAdapterNew = new ArticleRecyclerViewAdapter(PortailActivity.this, portailVo.getArticlesNouveau(), PortailActivity.this, lng.getLng(), 2);

                                recycler_article_new.setAdapter(articleRecyclerViewAdapterNew);
                                LinearLayoutManager layoutManager3 = new LinearLayoutManager(PortailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                recycler_article_new.setLayoutManager(layoutManager3);
                                if (portailVo.getArticlesNouveau().isEmpty())
                                    findViewById(R.id.layout_new).setVisibility(GONE);
                                else
                                    findViewById(R.id.layout_new).setVisibility(View.VISIBLE);

                                url_video = portailVo.getUrlVideo();
                                //Log.e("TAGS","VIDEO : " + portailVo.getAutoStart());
                                if(!url_video.isEmpty()){
                                    if(portailVo.getAutoStart().equalsIgnoreCase("1")) {
                                        openViewVideo();
                                    }
                                    findViewById(R.id.image_video).setVisibility(View.VISIBLE);
                                }*/
                            } catch (Exception e) {
                                e.printStackTrace();
                                //findViewById(R.id.progress_dossier).setVisibility(GONE);
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                         //   CommunConfigAndFonction.parseVolleyError(error, getApplicationContext());
                         //   findViewById(R.id.progress_dossier).setVisibility(GONE);
                        }
                    }
            );
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    CommunFunction.time_out,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonObjectRequest);
        }




    }
}