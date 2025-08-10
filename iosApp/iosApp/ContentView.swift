import SwiftUI
import Shared
import KMPObservableViewModelSwiftUI

struct ContentView: View {
    @StateViewModel
    private var viewModel = appComponent.landmarkListViewModel

    var body: some View {
        LandmarkList(viewModel: viewModel)
    }
}

#Preview {
    ContentView()
}
