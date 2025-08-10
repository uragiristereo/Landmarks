import SwiftUI

struct FavoriteButton: View {
    var isSet: Bool
    var onSetChange: () -> Void

    var body: some View {
        Button {
            onSetChange()
        } label: {
            Label("Toggle Favorite", systemImage: isSet ? "star.fill" : "star")
                .labelStyle(.iconOnly)
                .foregroundStyle(isSet ? .yellow : .gray)
        }
    }
}

#Preview {
    @Previewable
    @State
    var isSet = false

    FavoriteButton(
        isSet: isSet,
        onSetChange: {
            isSet = !isSet
        }
    )
}
