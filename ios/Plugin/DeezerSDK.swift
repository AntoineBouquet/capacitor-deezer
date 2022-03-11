import Foundation

@objc public class DeezerSDK: NSObject {
    var deezerConnect: DeezerConnect?
    
    static let appId = "531622"
    
    func initialize(appId: String) -> Bool {
        deezerConnect = DeezerConnect.init(appId: appId, andDelegate: self)
        DZRRequestManager.default().dzrConnect = deezerConnect
        return true;
    }
}


@objc extension DeezerSDK: DeezerSessionDelegate { 
    public func deezerDidLogin() {
        guard let deezerConnect = deezerConnect else {
            return
        }
        
        /*save(token: deezerConnect.accessToken, expirationDate: deezerConnect.expirationDate, userId: deezerConnect.userId)
        loginResult?(.success)*/
    }
    
    public func deezerDidNotLogin(_ cancelled: Bool) {
       /* let deezerError: Error? = cancelled ? nil : NSError.instance(type: .noConnection)
        loginResult?(.error(error: deezerError))*/
    }
    
    public func deezerDidLogout(){
        /*clearTokenAndExpirationDate()
        loginResult?(.logout)*/
    }
}


