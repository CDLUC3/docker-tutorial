FROM ruby:2.7

RUN mkdir /app
WORKDIR /app

COPY Gemfile Gemfile

RUN bundle config --local build.mysql2 --with-mysql2-config=/usr/lib64/mysql/mysql_config && \
    bundle config --local silence_root_warning true && \
    bundle install 

COPY . .

CMD ["bundle", "exec", "ruby", "app.rb"]