package com.code.common.repositories;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import com.code.designpatternapp.BuildConfig;


import com.code.designpatternapp.api.ApiEndpointInterface;
import com.code.designpatternapp.data.contracts.models.ImageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.code.common.contracts.DataModel;
import com.code.common.contracts.IModel;
import com.code.common.contracts.ModelState;
import com.code.common.contracts.Page;
import com.code.common.contracts.PageInput;
import com.code.common.contracts.RemoteData;
import com.code.common.helpers.DateHelper;
import com.code.common.helpers.MultipartUtility;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RemoteRepository<TModel extends IModel> {

    protected final Gson _gson;
    protected final Type _dataType;
    protected final Type _pageType;
    private final MediaType _mediaType = MediaType.parse("application/json; charset=utf-8");
    private final Context _context;
    private final String _key;
    private final String _url;
    OkHttpClient _httpClient = new OkHttpClient();
    String myVersionName=null;

    public RemoteRepository(Context context, String key, Type pageType, Type dataType) {
        _dataType = dataType;
        _gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateHelper()).create();

        _context = context;
        _key = key;
        _pageType = pageType;
        _url = ApiEndpointInterface.URL + "/" + _key;
            myVersionName = BuildConfig.VERSION_NAME;
            Log.d("version name" ,myVersionName);

    }

    public String rawPost(String data) {
        return rawPost(null, null, data);
    }

    public String rawPost(String data, String token) {
        return rawPost(token, null, data);
    }

    public String rawPost(String token, String action, String data) {
        String url = _url;
        if (action != null) {
            url = _url + "/" + action;
        }
        Log.i("POST:START", url);
        Log.d("POST:DATA", data);

        _httpClient.setConnectTimeout(15, TimeUnit.MINUTES);
        _httpClient.setReadTimeout(15, TimeUnit.MINUTES);
        try {
            RequestBody body = RequestBody.create(_mediaType, data == null ? "" : data);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("x-access-token", token)
                    .build();
            Response response = _httpClient.newCall(request).execute();
            String dataBody = response.body().string();
            Log.d("POST:DATA[" + url + "]", dataBody);
            return dataBody;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String rawPut(Long action, String data, String token) {
        _httpClient.setConnectTimeout(15, TimeUnit.MINUTES);
        _httpClient.setReadTimeout(15, TimeUnit.MINUTES);
        String url = _url + "/" + action;
        Log.i("PUT:START", url);
        Log.d("PUT:DATA", data);

        try {
            RequestBody body = RequestBody.create(_mediaType, data);
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("x-access-token", token)
                    .build();
            Response response = _httpClient.newCall(request).execute();
            String dataBody = response.body().string();
            Log.d("PUT:DATA[" + url + "]", dataBody);
            return dataBody;
        } catch (Exception ex) {
            ex.printStackTrace();
            RemoteData error = new RemoteData();
            error.isSuccess = false;
            error.error = ex.getMessage();
            return _gson.toJson(error);
        }
    }

    public String delete(Long id, String token) {
        String url = _url + "/" + id;
        Log.i("DELETE:START", url);


        _httpClient.setConnectTimeout(15, TimeUnit.MINUTES);
        _httpClient.setReadTimeout(15, TimeUnit.MINUTES);
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("x-access-token", token)
                    .delete()
                    .build();
            Response response = _httpClient.newCall(request).execute();

            String dataBody = response.body().string();
            Log.d("DELETE:DATA[" + url + "]", dataBody);
            return dataBody;

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.printStackTrace();
            RemoteData error = new RemoteData();
            error.isSuccess = false;
            error.error = ex.getMessage();
            return _gson.toJson(error);
        }
    }

    private String remoteGet(Hashtable<String, String> params, String action, String token) {
        return remoteGet(_url + "/" + action, params, token);
    }


    private String remoteGet(Hashtable<String, String> params, String token) {
        return remoteGet(_url, params, token);
    }


    private String remoteGet(String url, Hashtable<String, String> params, String token) {

        String query = "";

        if (params != null) {
            Uri.Builder uriBuilder = new Uri.Builder();

            for (String key : params.keySet()) {
                String value = params.get(key);
                if (!value.equals(""))
                    uriBuilder.appendQueryParameter(key, value);
            }

            query = uriBuilder.build().getEncodedQuery();
        }

        String getUrl = url + (query == "" ? "" : "?" + query);
        Log.i("GET:START", getUrl);


        _httpClient.setConnectTimeout(15, TimeUnit.MINUTES);
        _httpClient.setReadTimeout(15, TimeUnit.MINUTES);
        try {
            Request request = new Request.Builder()
                    .url(getUrl)
                    .addHeader("x-access-token", token)
                    .build();

            Response response = _httpClient.newCall(request).execute();
            String body = response.body().string();
            Log.d("GET:DATA[" + getUrl + "]", body);

            return body;

        } catch (Exception ex) {
            ex.printStackTrace();
            RemoteData error = new RemoteData();
            error.isSuccess = false;
            error.error = ex.getMessage();
            return _gson.toJson(error);
        }
    }

    public ImageModel upload(File file, String action, String token) throws RemoteException, IOException {

        String url;
        if (action != null) {
            url = _url + "/" + action + "?token=" + token;
        } else {
            url = _url + "?token=" + token;
        }
        Log.i("Upload:START", url);

        _httpClient.setConnectTimeout(15, TimeUnit.MINUTES);
        _httpClient.setReadTimeout(15, TimeUnit.MINUTES);


        MultipartUtility multipartUtility = new MultipartUtility(url, "UTF-8");
        multipartUtility.addFilePart("image", file);
        List<String> response = multipartUtility.finish();

        Type type = new TypeToken<DataModel<ImageModel>>() {
        }.getType();

        DataModel<ImageModel> responseData = _gson.fromJson(response.get(0), type);

        if (responseData.isSuccess) {
            return responseData.data;
        }

        throw new RemoteException(responseData.getError());

    }

    public TModel get(String id) throws RemoteException {
        return get(id, null);
    }

    public TModel get(String id, String action) throws RemoteException {
        return get(id, action,null);
    }

    public TModel get(String id, String action, String token) throws RemoteException {
        String response = getRaw(id, action,token);
        DataModel<TModel> responseData = _gson.fromJson(response, _dataType);

        if (responseData.isSuccess) {
            return responseData.data;
        }

        throw new RemoteException(responseData.getError());
    }


    public String getRaw(String id) {
        return getRaw(id, null);
    }

    public String getRaw(String id, String action, String token){
        return action == null ?
                remoteGet(_url + "/" + id, null, token) :
                remoteGet(_url + "/" + action + "/" + id, null, token);
    }

    public String getRaw(String id, String action) {
        return getRaw(id, action,null);
    }

    public Page<TModel> page(PageInput input) throws RemoteException {
        return page(input, _key);
    }

    public Page<TModel> page(PageInput input, String action) throws RemoteException {

        String response = pageRaw(input, action, " ");

        Page<TModel> responseData = _gson.fromJson(response, _pageType);

        responseData.Total = (long) responseData.items.size();

        if (responseData.isSuccess) {
            return responseData;
        }
        throw new RemoteException(responseData.getError());
    }

    public Page<TModel> page(String token, PageInput input, String action) throws RemoteException {

        String response = pageRaw(input, action, token);

        Page<TModel> responseData = _gson.fromJson(response, _pageType);

        responseData.Total = (long) responseData.items.size();

        if (responseData.isSuccess) {
            return responseData;
        }
        throw new RemoteException(responseData.getError());
    }

    public String pageRaw(PageInput input) {
        return pageRaw(input, null, null);
    }

    public String pageRaw(PageInput input, String action, String token) {
        return action == null ? remoteGet(input.getParams(), token) : remoteGet(input.getParams(), action, token);
    }


    public TModel update(TModel model, String token) throws RemoteException {
        Long trackingId = model.getServerId();
        if (trackingId == null) {
            return create(model, token);
        }
        String data = _gson.toJson(model);
        DataModel<TModel> responseData;
        responseData = _gson.fromJson(rawPut(trackingId, data, token), _dataType);
        if (responseData.isSuccess) {
            /*if(model.getModelStatus().equals(ModelState.deleted)){
                model.setStatus(ModelState.deleted .getValue());
            }else {
                model.setStatus(ModelState.synced.getValue());
            } */

            return model;
        }
        throw new RemoteException(responseData.getError());
    }

    public TModel create(TModel model, String token) throws RemoteException {

        return create(model, null,token);
    }

    public TModel create(TModel model, String action, String token) throws RemoteException {
        String data = _gson.toJson(model);

        String responseJson = rawPost(token, action, data);

        DataModel<TModel> responseData = _gson.fromJson(responseJson, _dataType);

        if (responseData.isSuccess) {
            responseData.data.setServerId(responseData.data.getId());
            responseData.data.setId(model.getId());
            responseData.data.setStatus(ModelState.synced.getValue());
            return responseData.data;
        }
        throw new RemoteException(responseData.getError());
    }
}
