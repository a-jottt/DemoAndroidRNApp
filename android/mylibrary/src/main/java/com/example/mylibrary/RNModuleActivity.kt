package com.example.mylibrary

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import com.oblador.vectoricons.VectorIconsPackage
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import com.th3rdwave.safeareacontext.SafeAreaContextPackage


class RNModuleActivity : AppCompatActivity(), DefaultHardwareBackBtnHandler {
    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)

        mReactRootView = RNGestureHandlerEnabledRootView(this)
        mReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackage(MainReactPackage())
            .addPackage(ReanimatedPackage())
            .addPackage(VectorIconsPackage())
            .addPackage(RNGestureHandlerPackage())
            .addPackage(AsyncStoragePackage())
            .addPackage(SafeAreaContextPackage())
            .addPackage(RNBasicPackage())
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()


        // Send message from native code
        val initialProperties = Bundle()
        initialProperties.putString("message_from_native", intent?.extras?.get("message_from_native")?.toString())

        (mReactRootView as RNGestureHandlerEnabledRootView).startReactApplication(mReactInstanceManager, "DemoRNApp", initialProperties)
        setContentView(mReactRootView)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostPause(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostResume(this, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostDestroy(this)
        }
        if (mReactRootView != null) {
            mReactRootView!!.unmountReactApplication()
        }
    }

    override fun onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager!!.showDevOptionsDialog()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
}