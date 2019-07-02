package com.example.baselib.net.observer;

import android.widget.Toast;

import com.example.baselib.context.Utils;
import com.example.baselib.net.BaseNetBean;
import com.example.baselib.net.exception.ApiException;
import com.google.gson.JsonParseException;
import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by liangweirong on 2018/5/3.
 *
 * 默认的解析器 不排除有一些特殊情况这个不适用
 *
 *
 */

public abstract class DefaultObserver<T extends BaseNetBean> implements Observer<BaseNetBean> {
    private boolean isShowTips=true;
    public DefaultObserver() {
    }
    public DefaultObserver(boolean isShowTips) {
        this.isShowTips=isShowTips;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseNetBean response) {
        if(response.isOk()){   //请求适正常的
            onSuccess((T) response);
        }else{
            //提示
            Toast.makeText(Utils.getContext(),response.getMessage(),Toast.LENGTH_SHORT).show();
        }
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
//        DebugUtil.error("Retrofit", e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else if(e instanceof ApiException){  //返回的状态码不对

        }else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

//    /**
//     * 服务器返回数据，但响应码不为200
//     *
//     */
//    public void onFail(String message) {
//        ToastUtils.show(message);
//    }

    public void onFinish(){}

    /**
     * 返回的状态码不是0
     */
    public void onStatusException(){

    }


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
//        ToastUitl.showShort(R.string.net_error);
        Toast.makeText(Utils.getContext(),"网络访问错误，请稍后再试",Toast.LENGTH_SHORT).show();
//        switch (reason) {
//            case CONNECT_ERROR:
//                ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT);
//                break;
//
//            case CONNECT_TIMEOUT:
//                ToastUtils.show(R.string.connect_timeout, Toast.LENGTH_SHORT);
//                break;
//
//            case BAD_NETWORK:
//                ToastUtils.show(R.string.bad_network, Toast.LENGTH_SHORT);
//                break;
//
//            case PARSE_ERROR:
//                ToastUtils.show(R.string.parse_error, Toast.LENGTH_SHORT);
//                break;
//
//            case UNKNOWN_ERROR:
//            default:
//                ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT);
//                break;
//        }

    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}