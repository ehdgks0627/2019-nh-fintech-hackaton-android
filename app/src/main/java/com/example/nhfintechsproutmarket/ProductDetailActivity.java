package com.example.nhfintechsproutmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity {


    @BindView(R.id.pd_item_title)
    TextView titleView;
    @BindView(R.id.pd_item_description)
    TextView descriptionView;
    @BindView(R.id.pd_item_price)
    TextView priceView;
    @BindView(R.id.pd_item_image)
    ImageView imageView;
    @BindView(R.id.pd_item_detail)
    ImageView detailView;

    @BindView(R.id.btn_buy)
    Button btnBuy;
    String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        productId = intent.getStringExtra("product_id");
        String url = getString(R.string.server_url) + "/product/detail/" + productId;
        JsonRequest<JSONObject> request = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    titleView.setText(response.getJSONObject("data").getJSONObject("product").getString("product_category"));
                                    descriptionView.setText(response.getJSONObject("data").getJSONObject("product").getString("product_name"));
                                    priceView.setText(response.getJSONObject("data").getJSONObject("product").getString("product_price"));
                                    byte[] decodedString = Base64.decode(response.getJSONObject("data").getJSONObject("product").getString("product_photo"), Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                    imageView.setImageBitmap(decodedByte);
                                    new DownloadImageTask(imageView);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        // Add the request to the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

        btnBuy.setOnClickListener(view -> {
            String url1 = getString(R.string.server_url) + "/cart/";
            StringRequest postRequest = new StringRequest(Request.Method.PUT, url1,
                    response -> {
                        // response
                        Toast.makeText(getApplicationContext(), "카트에 담겼습니다", Toast.LENGTH_LONG).show();
                    },
                    error -> {
                        // error
                        Toast.makeText(getApplicationContext(), "카트에 담기 실패했습니다", Toast.LENGTH_LONG).show();
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<>();
                    params.put("product_id", productId);
                    return params;
                }
            };

            queue.add(postRequest);
        });
    }
}
