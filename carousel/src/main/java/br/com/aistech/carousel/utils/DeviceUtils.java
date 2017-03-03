package br.com.aistech.carousel.utils;

import android.content.Context;
import android.view.Surface;
import android.view.WindowManager;

import br.com.aistech.carousel.enums.DeviceOrientation;


/**
 * Created by jonathan on 02/03/17.
 */

public class DeviceUtils {

    public static DeviceOrientation getDeviceOrientation(Context context) {
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return DeviceOrientation.PORTRAIT;
            case Surface.ROTATION_90:
                return DeviceOrientation.LANDSCAPE;
            case Surface.ROTATION_180:
                return DeviceOrientation.PORTRAIT;
            default:
                return DeviceOrientation.LANDSCAPE;
        }
    }
}
