---
title: "VSCode and Docker"
nextpage: ../resources
---

{% include nav.html %}

## VSCode has some really useful features for working with Docker Objects
[Containers in VSCode](https://code.visualstudio.com/docs/containers/overview)

Our project has been configured with an awareness of our docker configurations
- [VSCode Docker Menu Entries for this repo](https://github.com/CDLUC3/docker-tutorial/blob/main/.vscode/settings.json)

## Start a Stack

The top button in the ribbon on the left is called "Explorer".  Click that button.

- Navigate to **examples/session2/docker-compose.yml**
- Right click the file
- Click "Compose Up"
- Click "simpsons stack" in the popup window

Notice that the output from the `docker-compose up` command appears in a terminal panel.

If any errors occur, there may be resources active from the Session 2 exercises.  We'll discover those in VSCode!

## Docker Panel

Click the "Docker" button in the ribbon on the left.  This will reveal several panels.

- Containers
- Images
- Registries
- Networks
- Volumes

These panels provide an intuitive GUI for browsing your docker assets.

## Tasks to perform

### Explore Containers
- Right Click a container, View Logs
- Expand a container in the panel, browse the file system
- Right Click the "my-mysql" container
  - Select "Attach Shell"
  - Select "mysql session" from the popup box
  - Enter `show tables;` in the SQL terminal window
  - Enter `exit` to close the SQL session
- Right Click the "myrbuy" container
  - Select "default"
  - Enter `gem list` in the container terminal window
  - Enter `exit` to close the terminal session
- Right Click the "myruby" container
  - Select "Attach Visual Studio Code"
  - A new window will open
  - Click the Explorer button in the new window
  - Open the folder "/app"
  - From the menu bar, select "New Terminal"
  - Enter `gem list` in the terminal

### Explore Other Objects
- Images
- Networks
- Volumes

### Stop the Containers
- Navigate to **examples/session2/docker-compose.yml**
- Right click the file
- Click "Compose Down"
- Click "simpsons stack" in the popup window

### Note the resource changes
- Containers and network are removed
- Images and volumes persist

## Additional Reading
Using a container as a shared development environment
- [Sharing a Container as a Dev Environment](https://www.docker.com/blog/how-to-develop-inside-a-container-using-visual-studio-code-remote-containers/)

{% include next.html %}
