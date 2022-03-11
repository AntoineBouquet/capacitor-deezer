import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(DeezerSDKPlugin)
public class DeezerSDKPlugin: CAPPlugin {
    private let implementation = DeezerSDK()

    @objc func initialize(_ call: CAPPluginCall) {
        let appId = call.getString("appId") ?? ""
        
        let resInit: Bool = implementation.initialize(appId: appId);
        call.resolve([
            "value": resInit
        ])
        
    }
}
