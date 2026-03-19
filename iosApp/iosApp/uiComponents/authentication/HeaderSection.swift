import SwiftUI

struct HeaderSection: View {
    var body: some View {
        VStack(spacing: 8) {
            Image(systemName: "heart.text.square.fill")
                .resizable()
                .frame(width: 80, height: 80)
                .foregroundColor(.blue)
            Text("Your Family’s Healthcare in One App")
                .font(.title2)
                .fontWeight(.semibold)
                .multilineTextAlignment(.center)
            Text("Manage appointments, access records, and consult doctors securely.")
                .font(.subheadline)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
        }
    }
}