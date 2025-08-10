import SwiftUI
import Shared
import KMPObservableViewModelSwiftUI

struct LandmarkList: View {
    @StateViewModel
    var viewModel: LandmarkListViewModel

    var body: some View {
        NavigationSplitView {
            List {
                Toggle(
                    isOn: Binding(
                        get: {
                            viewModel.showFavoritesOnly
                        },
                        set: { _ in
                            viewModel.toggleShowFavoritesOnly()
                        }
                    )
                ) {
                    Text("Favorites only")
                }

                ForEach(viewModel.filteredLandmarks, id: \.id) { landmark in
                    NavigationLink {
                        LandmarkDetail(
                            viewModel: appComponent.landmarkDetailViewModel(KotlinInt(value: landmark.id))
                        )
                    } label: {
                        LandmarkRow(landmark: landmark)
                    }
                }
            }
            .animation(.default, value: viewModel.filteredLandmarks)
            .navigationTitle("Landmarks")
        } detail: {
            Text("Select a Landmark")
        }
    }
}

#Preview {
    @Previewable
    @StateViewModel
    var viewModel = appComponent.landmarkListViewModel

    LandmarkList(viewModel: viewModel)
}
