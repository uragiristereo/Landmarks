import SwiftUI
import Shared

struct LandmarkRow: View {
    var landmark: Landmark

    var body: some View {
        HStack {
            Image(landmark.imageName)
                .resizable()
                .frame(width: 50, height: 50)

            Text(landmark.name)

            Spacer()

            if landmark.isFavorite {
                Image(systemName: "star.fill")
                    .foregroundStyle(.yellow)
            }
        }
    }
}

#Preview {
    let landmarks = LandmarkDataKt.landmarkData

    Group {
        LandmarkRow(landmark: landmarks[0])

        LandmarkRow(landmark: landmarks[1])
    }
}
