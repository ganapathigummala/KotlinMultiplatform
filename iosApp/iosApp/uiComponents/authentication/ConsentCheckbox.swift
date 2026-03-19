
struct ConsentCheckbox: View {
    @Binding var isChecked: Bool
    let error: String?

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            HStack(alignment: .top, spacing: 8) {
                // Custom checkbox
                Image(systemName: isChecked ? "checkmark.square.fill" : "square")
                    .foregroundColor(isChecked ? .blue : .gray)
                    .font(.title3)
                    .onTapGesture {
                        isChecked.toggle()
                    }

                // Attributed text for links
                Text("I agree to the ") +
                Text("Terms of Service")
                    .foregroundColor(.blue)
                    .underline() +
                Text(" and ") +
                Text("Privacy Policy")
                    .foregroundColor(.blue)
                    .underline()
                    .onTapGesture {
                        // Handle link tap – you'd navigate to a web view
                        // This is simplified; in reality you'd use a Button or detect which part was tapped
                    }
            }

            if let error = error {
                Text(error)
                    .font(.caption)
                    .foregroundColor(.red)
                    .padding(.leading, 28)
            }
        }
    }
}