---
title: "GitHub Actions and Docker"
nextpage: package.lambda.with.docker
---

{% include nav.html %}

## GitHub Actions

GitHub actions are automations that run on GitHub servers when a repository is modified.  These actions are similar to the Travis.com unit tests that we run on the UC3 Merritt UI code as it is modified.

GitHub actions are general purpose and can be written to perform any type of automation.  These automations can run as Linux actions or as Docker actions.

## Automation Scenario

In this example, we would like to generate additional test files that can be utilized in our test docker stack.

We will create a yaml file ([examples/session3/contacts.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/contacts.yml)) that contains a set of data that we would like to use for testing.

## Yaml to CSV Conversion Program

The following python program can be utilized to convert the yaml file into a set of csv files.

- [examples/session3/docker-action/yaml2csv.py](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/docker-action/yaml2csv.py)

_If you have python3 and pyyaml installed, you can try running it locally._

```
python3 examples/session3/docker-action/yaml2csv.py examples/session3/contacts.yml
```

If you do not, you can run it in docker!
```
docker build -t my-yaml2csv examples/session3/docker-action
```

```
docker run --rm -it -v "$(pwd)/examples/session3/contacts.yml:/app/contacts.yml" my-yaml2csv
```

## Adding this Docker Image as a GitHub Action

[GitHub Actions - Container Actions](https://docs.github.com/en/free-pro-team@latest/actions/creating-actions/creating-a-docker-container-action)

The following [examples/session3/docker-action/action.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/docker-action/action.yml) describes how to run the Docker image as a GitHub action.

[.github/workflows/main.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/.github/workflows/main.yml) defineds a GitHub actiont that will execute this action on any push to the repository.

The results will be visible on the repository [Actions Tab](https://github.com/CDLUC3/docker-tutorial/actions).

## Try it Yourself

- For this repository to your own account
- Go to the Actions tab of your fork
- Enable workflows
- Make a commit to the repository
- View the results

## Potential uses for UC3

We could add a GitHub action to perform Yaml file validations that must pass before deployment.

{% include next.html %}
