# Bizzy Cards for Android
Welcome to Bizzy Cards' open source Android app!

## Content
- [About Bizzy Cards - Website](https://bizzycards.github.io)
- [Development](#development)
- Design
    - [Figma document](https://www.figma.com/file/KionIqUJy75l43IRbfQp9zKn/Bizzy-Cards)
    - [Figma interactive prototype](https://www.figma.com/proto/KionIqUJy75l43IRbfQp9zKn/Bizzy-Cards?scaling=contain&node-id=1%3A3)
- [Features](#features)
- [Technical implementation](#technical-documentation)
- [Authors](https://github.com/jorge-sanz/bizzy-cards-android/graphs/contributors)
- [License]()
- [Acknowledgments](#acknowledgments)

## Development
### Getting started
Follow the next steps to get the project ready for development:
1. Clone this repository.
2. Download the appropriate [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) for your system. We are currently on JDK 8.
3. [Install Android Studio](https://developer.android.com/sdk/index.html).
4. Import the project. Open Android Studio, click `Open an existing Android Studio project` and select the project. Gradle will build the project.
5. Run the app. Click `Run > Run 'app'`. After the project builds you'll be prompted to build or launch an emulator.

## Features
Project and product management is done on [out Trello board](https://trello.com/b/qXWG60vh). App features:

- [ ] Add new card via QR code.
- [ ] Show your QR code.
- [ ] Edit your personal card.
- [ ] Card list.
- [ ] Detailed card view.
- [ ] Get people's avatar automatically from Gravatar.
- [ ] Send your card via NFC.
- [ ] Receive new card via NFC.
- [ ] Share other people's card.

## Technical documentation
### Architecture
Bizzy Cards for Android is implemented following [the recommended app architecure by Google](https://developer.android.com/topic/libraries/architecture/guide.html#recommended_app_architecture) using the new [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html).

## Acknowledgements
- [fab-speed-dial](https://github.com/yavski/fab-speed-dial)
- [barcodescanner](https://github.com/dm77/barcodescanner)
- [Google Codelabs - Android Room with a View](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..%2Findex#0)
