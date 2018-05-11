[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# TodoMVC with Vaadin Flow Java - only

A TodoMVC trying very strictly follow the specs, but still done with server-side Java only

# With some basic offline detection

Has simple detection of offline/online:
- In case of offline, a static offline-page is shown
- When switched back to online, page is refreshed to normal state

For instance, fig 1 something todo:

![basic-view](https://user-images.githubusercontent.com/19262966/39934250-e5e6c810-554d-11e8-8b99-869c767ecebc.png)

To switch to offline-mode (with Chrome), dev-tools, network

![chrome-online](https://user-images.githubusercontent.com/19262966/39934273-fb410f0e-554d-11e8-85d0-c68888f527c2.png)

Check the offline

![chrome-offline](https://user-images.githubusercontent.com/19262966/39934284-031ff776-554e-11e8-9b41-2b093e2513f5.png)

And one a refresh should happen so that a static webpage is served

![offline-view](https://user-images.githubusercontent.com/19262966/39934258-efca0ba8-554d-11e8-9c20-d0815d973b58.png)


- To be noted, this is merely testing, not a fully working solution

## Running the Project

1. Run using `mvn jetty:run`
2. Wait for the application to start
3. Open http://localhost:8080/ to view the application
