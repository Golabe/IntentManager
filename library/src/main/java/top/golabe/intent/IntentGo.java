package top.golabe.intent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class IntentGo {

    private final int DEFAULT_RESULT_CODE = -0x00111;

    private Map<String, Object> mParams = null;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;
    private Class<?> mTargetClass = null;
    private int mResultCode = DEFAULT_RESULT_CODE;
    private Pair[] mPairs = null;

    public void go() {
        Intent intent = new Intent(mCtx, mTargetClass);

        if (mParams != null && mParams.size() > 0) {
            Bundle bundle = new Bundle();

            for (Map.Entry<String, Object> param : mParams.entrySet()) {

                Object value = param.getValue();
                String key = param.getKey();
                if (value instanceof String) {
                    bundle.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    bundle.putInt(key, (Integer) value);
                } else if (value instanceof Long) {
                    bundle.putLong(key, (Long) value);
                } else if (value instanceof Float) {
                    bundle.putFloat(key, (Float) value);
                } else if (value instanceof Serializable) {
                    bundle.putSerializable(key, (Serializable) value);
                } else if (value instanceof Parcelable) {
                    bundle.putParcelable(key, (Parcelable) value);
                } else {
                    throw new IllegalArgumentException("不支持的数据类型");
                }
            }
            intent.putExtras(bundle);
        }


        if (mPairs != null && mPairs.length > 0) {

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mCtx, mPairs);

            if (mResultCode != DEFAULT_RESULT_CODE) {
                ActivityCompat.startActivityForResult((Activity) mCtx, intent, mResultCode, optionsCompat.toBundle());
            } else {
                ActivityCompat.startActivity(mCtx, intent, optionsCompat.toBundle());
            }

        } else if (mResultCode != DEFAULT_RESULT_CODE) {

            ((Activity) mCtx).startActivityForResult(intent, mResultCode);

        } else {

            mCtx.startActivity(intent);

        }

    }

    public IntentGo target(Class<?> cls) {
        this.mTargetClass = cls;
        return this;
    }

    public IntentGo params(String key, Object value) {
        if (mParams == null) {
            mParams = new HashMap<>();
        }
        mParams.put(key, value);
        return this;
    }

    public IntentGo targetForResult(Class<?> cls, int resultCode) {
        this.mTargetClass = cls;
        this.mResultCode = resultCode;
        return this;
    }

    @SafeVarargs
    public final IntentGo makeSceneTransitionAnimation(Pair<View, String>... sharedElements) {
        if (mPairs == null) {
            mPairs = new Pair[sharedElements.length];
        }
        for (int i = 0; i < sharedElements.length; i++) {
            mPairs[i] = Pair.create(
                    sharedElements[i].first, sharedElements[i].second);
        }
        return this;
    }

    private IntentGo() {
    }

    public static IntentGo with(Context context) {
        mCtx = context;
        return new IntentGo();
    }


}
