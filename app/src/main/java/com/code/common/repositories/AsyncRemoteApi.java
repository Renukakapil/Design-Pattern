package com.code.common.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.RemoteException;
import android.util.Log;

import com.code.common.contracts.AsyncNotify;
import com.code.common.contracts.AsyncResult;
import com.code.common.contracts.DataModel;
import com.code.common.contracts.IAsyncRemoteApi;
import com.code.common.contracts.IAsyncResponse;
import com.code.common.contracts.IModel;
import com.code.common.contracts.Page;
import com.code.common.contracts.PageInput;
import com.code.designpatternapp.BuildConfig;
import com.code.designpatternapp.data.contracts.models.ImageModel;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Type;

public class AsyncRemoteApi<TModel extends IModel> implements IAsyncRemoteApi<TModel> {
    public static String TAG=AsyncRemoteApi.class.getSimpleName();
    protected static final String ERROR_NOT_CONNECTED = "Internet connection not available";
    protected static final String ERROR_UNKNOWN = "Unknown error";
    protected final RemoteRepository<TModel> _remoteRepository;
    protected final CachedRepository<TModel> _cachedRepository;

    protected Gson _gson;
    protected Type _dataType;
    protected Context mContext;

    public AsyncRemoteApi(Context context,
                          String key,
                          Type modelType,
                          Type pageType,
                          Type dataType,
                          SQLiteDatabase database) {
        _remoteRepository = new RemoteRepository<>(context, key, pageType, dataType);
        _cachedRepository = new CachedRepository<>(key, modelType, pageType, database);

        _dataType = dataType;
        mContext=context;

        _gson = new Gson();

    }


    @Override
    public IAsyncResponse<TModel> get(final String id) {
        final ModelResponse response = new ModelResponse();
        response._isBusy = true;

        response._model = _cachedRepository.get(id);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response._model = _remoteRepository.get(id);
                    _cachedRepository.update(id, response._model);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    response._error = ERROR_UNKNOWN;
                } finally {
                    response._isBusy = false;
                }
            }
        })).start();

        return response;
    }

    @Override
    public void get(AsyncResult<TModel> result) {
        get(null,null,result);
    }

    @Override
    public void get(String id, AsyncResult<TModel> result) {
        get(id, null, result);
    }

    @Override
    public void get(final String id, final String action, final AsyncResult<TModel> result) {
        get(id, action,"token",result); //token to get from preference

    }

    @Override
    public void get(final String id, final String action, final String token, final AsyncResult<TModel> result) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TModel model = _remoteRepository.get(id, action,token);
                    result.success(model);
                } catch (RemoteException e){
                    e.printStackTrace();
                    result.error(e.getMessage());
                }catch (Exception ex) {
                    ex.printStackTrace();
                    Log.e(TAG,"Exception raised" +ex.getMessage());
                    result.error("Unknown Error");
                }
            }
        })).start();
    }

    @Override
    public IAsyncResponse<TModel> update(final TModel model, final String token) {
        final ModelResponse response = new ModelResponse();
        response._isBusy = true;

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response._model = _remoteRepository.update(model,token);
//                    _cachedRepository.update(model.getId(), response._model);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    response._error = ERROR_UNKNOWN;
                } finally {
                    response._isBusy = false;
                }
            }
        })).start();

        return response;
    }

    @Override
    public void update(final TModel model, final AsyncResult<TModel> result) {

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TModel remoteModel = _remoteRepository.update(model,"token");
//                    _cachedRepository.update(model.getId(), remoteModel);
                    result.success(remoteModel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                  /*  if(Config.isDevelopEnv){
                        result.error(ex.getMessage());
                    } else {
                        result.error(ERROR_UNKNOWN);
                    }*/
                }
            }
        })).start();

    }

    @Override
    public IAsyncResponse<Page<TModel>> page(final PageInput input) {
        final PageResponse response = new PageResponse();
        response._isBusy = true;

        response._page = _cachedRepository.page(input);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response._page = _remoteRepository.page(input);
                    _cachedRepository.update(input, response._page);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    response._error = ERROR_UNKNOWN;
                } finally {
                    response._isBusy = false;
                }
            }
        })).start();

        return response;
    }


    @Override
    public void page(PageInput input, AsyncResult<Page<TModel>> result) {
        page(input, null,"token", result);
    }

    @Override
    public void page(final PageInput input, final String action, final AsyncResult<Page<TModel>> result) {
       page(input,action,"token",result);
    }

    @Override
    public void page(final PageInput input, final String action, final String token, final AsyncResult<Page<TModel>> result) {

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Page<TModel> realPage = _remoteRepository.page(token, input,action);
                    result.success(realPage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result.error(ex.getMessage());
                }
            }
        })).start();
    }


  /*  @Override
    public IAsyncResponse<TModel> create(final TModel model) {
        final ModelResponse response = new ModelResponse();
        response._isBusy = true;

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response._model = _remoteRepository.create(model,"");
//                    _cachedRepository.update(model.getId(), response._model);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    response._error = ERROR_UNKNOWN;
                } finally {
                    response._isBusy = false;
                }
            }
        })).start();

        return response;
    }
    */

    @Override
    public void create(final TModel model, final AsyncResult<TModel> result) {

        create(model,"token",null,result);
    }

    @Override
    public void create(final TModel model, final String action, final AsyncResult<TModel> result) {
       create(model, "token", action,result);
    }

    @Override
    public void create(final TModel model, final String token, final String action, final AsyncResult<TModel> result) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TModel remoteModel = _remoteRepository.create(model,action,token);
                    result.success(remoteModel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result.error(ERROR_UNKNOWN);
                }
            }
        })).start();
    }

    @Override
    public void delete(final Long id, final String token, final AsyncNotify result ) {

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String responseJson = _remoteRepository.delete(id,token);
                    DataModel<TModel> responseData = _gson.fromJson(responseJson, _dataType);
                    //_cachedRepository.remove(id, remoteModel);
                    if (responseData.isSuccess)
                        result.success();
                    else
                        result.error(responseData.message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                  /*  if(Config.isDevelopEnv){
                        result.error(ex.getMessage());
                    } else {
                        result.error(ERROR_UNKNOWN);
                    }*/
                }
            }
        })).start();

    }

    public class ModelResponse implements IAsyncResponse<TModel> {
        boolean _isBusy;
        String _error;
        TModel _model;

        @Override
        public boolean isBusy() {
            return _isBusy;
        }

        @Override
        public boolean hasError() {
            return _error != null;
        }

        @Override
        public String getError() {
            return _error;
        }

        @Override
        public void waitForResult() {
            int i = 0;
            while (_isBusy) {
                i++;
                System.out.println(i);
            }
        }

        @Override
        public TModel getResult() {
            return _model;
        }
    }

    private class PageResponse implements IAsyncResponse<Page<TModel>> {
        boolean _isBusy;
        String _error;
        Page<TModel> _page;

        @Override
        public boolean isBusy() {
            return _isBusy;
        }

        @Override
        public boolean hasError() {
            return _error != null;
        }

        @Override
        public String getError() {
            return _error;
        }

        @Override
        public void waitForResult() {
            while (_isBusy) {
            }
        }

        @Override
        public Page<TModel> getResult() {
            return _page;
        }
    }

    @Override
    public void upload(final String action, final File file, final AsyncResult<ImageModel> result){


        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ImageModel response = _remoteRepository.upload( file, action,"token");
                    result.success(response);


                } catch (Exception ex) {
                    ex.printStackTrace();
                    if(BuildConfig.DEBUG){
                        result.error(ex.getMessage());
                    }else{
                        result.error(ERROR_UNKNOWN);
                    }
                }
            }
        })).start();
    }
}
