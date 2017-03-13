package com.sola.github.data.net;

import android.content.Context;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sola.github.data.net.tools.GSONConverter;
import com.sola.github.data.utils.ContextUtils;
import com.sola.github.tools.utils.LogUtils;
import com.sola.github.tools.utils.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Sola
 * 2017/2/23.
 */
@SuppressWarnings("unused")
public class ApiHttpsConnection extends AApiConnection {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private OkHttpClient httpClient;

    private final Retrofit.Builder builder;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    ApiHttpsConnection(
            Context context,
            ContextUtils contextUtils) {
        super(context, contextUtils);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GSONConverter.create(gson));
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public <S> S createService(String baseUrl, Class<S> serviceCls) {
        buildSSlClient();
        Retrofit retrofit = builder.baseUrl(baseUrl).client(httpClient).build();
        return retrofit.create(serviceCls);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * 关于Https的SSl证书认证的问题，这里暂且找不到好办法替代
     */
    private void buildSSlClient() {
        checkHttpClient();
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    // 这里不添加Https证书校验
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    // 这里不添加Https证书校验
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hv1 = (hostname, session) -> true;

        String workerClassName = "okhttp3.OkHttpClient";
        try {
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(httpClient, hv1);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(httpClient, sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkHttpClient() {
        if (httpClient != null)
            return;
        httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    /**
     * 监听请求日志用的类
     */
    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long t1 = System.nanoTime();
            Request.Builder ongoing = chain.request().newBuilder();
            Pair<String, Integer> versionInfo = getContextUtils().getVersionInfo();
            if (versionInfo != null) {
                ongoing.addHeader("appVersion", versionInfo.first);
                ongoing.addHeader("apiVersion", "" + versionInfo.second);
            }
            String myApiKey = getContextUtils().getAppChannelID();
            if (!StringUtils.isEmpty(myApiKey))
                ongoing.addHeader("channelId", myApiKey);
            ongoing.addHeader("deviceType", "Android");
            String deviceId = getContextUtils().getDeviceId();
            if (!StringUtils.isEmpty(deviceId))
                ongoing.addHeader("deviceId", deviceId);
            Request request = ongoing.build();
            LogUtils.d("OkHttp", String.format("Sending request %s \n %s",
                    request.url(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            LogUtils.d("OkHttp", String.format(Locale.getDefault(),
                    "Received response for %s in %.1fms ",
                    response.request().url(),
                    (t2 - t1) / 1e6d));
            return response;
        }
    }
}
