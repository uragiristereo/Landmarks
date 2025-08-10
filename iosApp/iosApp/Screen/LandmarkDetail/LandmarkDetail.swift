import SwiftUI
import Shared
import KMPObservableViewModelSwiftUI
import CoreLocation

struct LandmarkDetail: View {
    @StateViewModel
    var viewModel: LandmarkDetailViewModel

    var body: some View {
        if let landmark = viewModel.landmark {
            ScrollView {
                MapView(
                    coordinate: CLLocationCoordinate2D(
                        latitude: landmark.coordinates.latitude,
                        longitude: landmark.coordinates.longitude
                    )
                )
                    .frame(height: 300)

                CircleImage(image: Image(landmark.imageName))
                    .offset(y: -130)
                    .padding(.bottom, -130)

                VStack(alignment: .leading) {
                    HStack {
                        Text(landmark.name)
                            .font(.title)

                        FavoriteButton(
                            isSet: landmark.isFavorite,
                            onSetChange: {
                                viewModel.toggleFavorite()
                            }
                        )
                    }

                    HStack {
                        Text(landmark.park)
                            .font(.subheadline)

                        Spacer()

                        Text(landmark.state)
                            .font(.subheadline)
                    }

                    Divider()

                    Text("About \(landmark.name)")
                        .font(.title2)

                    Text(landmark.desc)
                }
                .padding()
            }
            .navigationTitle(landmark.name)
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

#Preview {
    @Previewable
    @StateViewModel
    var viewModel = appComponent.landmarkDetailViewModel(KotlinInt(value: LandmarkDataKt.landmarkData[0].id))

    LandmarkDetail(viewModel: viewModel)
}
