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
- Download the generated assets

## Optional Excercise - Run a docker compose stack in GitHub actions

It is also possible to use a docker compose stack within a GitHub actions.

Add the following step to `.github/workflows/main.yml` to test the generated text files.

```yaml
    - name: Test generated artifacts in docker stack
      run: |
        docker-compose -f ./examples/session2/servers.yml up -d
        sleep 10
        for file in output/*.txt
        do 
          docker-compose \
            -f examples/session2/servers.yml \
            -f examples/session3/docker-action-compose/loader.yml \
            run data-load /tmp/${file}
        done
        docker-compose -f ./examples/session2/servers.yml down
```

Note in the job output that the 4 generated files were successfully processed.

```output
Creating session2_data-load_run ... 

Creating session2_data-load_run ... done
Import the contents of [/tmp/output/bouviers.txt]
    1	Jacqueline	   Bouvier	               maude@yahoo.com	555-444-1111
    2	     Patty	   Bouvier	     patty@springfield-dot.com	555-444-1111
    3	     Selma	   Bouvier	     selma@springfield-dot.com	555-444-1111
Creating session2_data-load_run ... 

Creating session2_data-load_run ... done
Import the contents of [/tmp/output/flanders.txt]
    4	       Ned	  Flanders	                 ned@yahoo.com	555-333-1111
    5	     Maude	  Flanders	               maude@yahoo.com	555-333-1111
		Email [maude@yahoo.com] already exists for user id 5
    6	      Rodd	  Flanders	                              	          
    7	      Todd	  Flanders	                              	          
Creating session2_data-load_run ... 

Creating session2_data-load_run ... done
Import the contents of [/tmp/output/simpsons.txt]
    8	     Homer	   Simpson	              homer@powerplant	555-111-2222
    9	     Marge	   Simpson	                 marge@aol.com	555-111-2222
    9	     Marge	   Simpson	               marge@yahoo.com	555-323-2222
   10	      Bart	   Simpson	          bart@springfield.edu	          
   11	      Lisa	   Simpson	          lisa@springfield.edu	          
   12	    Maggie	   Simpson	                              	          
Creating session2_data-load_run ... 

Creating session2_data-load_run ... done
Import the contents of [/tmp/output/unknown.txt]
   13	    Sherri	   Unknown	        sherri@springfield.edu	          
   14	     Terri	   Unknown	         terri@springfield.edu	          
Stopping rubyserver ... 
Stopping mydb       ... 

Stopping rubyserver ... done

Stopping mydb       ... done
Removing rubyserver ... 
Removing mydb       ... 

Removing mydb       ... done

Removing rubyserver ... done
Removing network session2_mynet
```
## Potential uses for UC3

We could add a GitHub action to perform Yaml file validations that must pass before deployment.

{% include next.html %}
