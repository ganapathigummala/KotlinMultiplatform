import SwiftUI


struct MobileNumberInput: View {
    @Binding var countryCode: String
    @Binding var mobileNumber: String
    let error: String?

    // Example country codes
    let countryCodes = ["+1", "+44", "+91", "+61"]

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            HStack {
                // Country code menu
                Menu {
                    ForEach(countryCodes, id: \.self) { code in
                        Button(code) {
                            countryCode = code
                        }
                    }
                } label: {
                    HStack {
                        Text(countryCode)
                        Image(systemName: "chevron.down")
                    }
                    .padding(.horizontal, 12)
                    .padding(.vertical, 14)
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
                }

                // Mobile number text field
                TextField("Mobile number", text: $mobileNumber)
                    .keyboardType(.numberPad)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(8)
            }

            if let error = error {
                Text(error)
                    .font(.caption)
                    .foregroundColor(.red)
                    .padding(.leading, 4)
            }
        }
    }
}