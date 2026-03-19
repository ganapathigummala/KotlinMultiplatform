import SwiftUi

struct LoginLink: View {
    let action: () -> Void

    var body: some View {
        HStack {
            Text("Already have an account?")
            Button("Login", action: action)
                .fontWeight(.semibold)
        }
        .font(.subheadline)
    }
}