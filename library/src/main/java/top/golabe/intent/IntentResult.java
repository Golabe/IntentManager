package top.golabe.intent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class IntentResult {
    private Map<String, Object> mParams;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;

    public static IntentResult with(Context context) {
        mCtx = context;
        return new IntentResult();
    }

    public IntentResult result(int resultCode) {

        resultData(resultCode);
        return this;
    }

    private void resultData(int resultCode) {
        if (mParams != null && mParams.size() > 0) {
            Intent intent = new Intent();
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
            ((Activity) mCtx).setResult(resultCode, intent);
        }
    }

    public IntentResult result() {
        result(RESULT_OK);
        return this;
    }

    public IntentResult params(String key, Object value) {
        if (mParams == null) {
            mParams = new HashMap<>();
        }
        mParams.put(key, value);
        return this;
    }
}
