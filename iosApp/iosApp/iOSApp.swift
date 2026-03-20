import SwiftUI
import Shared
@main
struct iOSApp: App {
    init(){
    AppDiSetUp.doInitKoin(  )
    }
    var body: some Scene {
        WindowGroup {
            AuthenticationScreen()
        }
    }
}