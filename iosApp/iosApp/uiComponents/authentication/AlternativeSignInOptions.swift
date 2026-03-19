import SwiftUi

struct AlternativeSignInOptions: View {
    let onGoogle: () -> Void
    let onEmailPassword: () -> Void

    var body: some View {
        VStack(spacing: 16) {
            HStack {
                VStack { Divider() }
                Text("or continue with")
                    .font(.caption)
                    .foregroundColor(.secondary)
                VStack { Divider() }
            }

            Button(action: onGoogle) {
                HStack {
                    Image(systemName: "g.circle.fill") // Replace with actual Google logo
                        .font(.title2)
                    Text("Sign in with Google")
                        .fontWeight(.medium)
                }
                .frame(maxWidth: .infinity)
                .padding()
                .background(Color(.systemGray5))
                .cornerRadius(8)
            }

            Button(action: onEmailPassword) {
                Text("Login using Email & Password")
                    .fontWeight(.medium)
                    .foregroundColor(.blue)
            }
        }
    }
}