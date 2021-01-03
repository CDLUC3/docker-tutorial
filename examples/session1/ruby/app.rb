require 'sinatra'
require 'sinatra/base'
require 'mysql2'

set :bind, '0.0.0.0'

def contact_list
  cli = Mysql2::Client.new(
    :host => 'mydb',
    :username => 'user',
    :database=> 'userdb',
    :password=> 'password'
  )
  sql = %{
    select 
      id,
      first_name,
      last_name,
      phone,
      email
    from 
      users
    left join phone
      on phone.user_id = users.id
    left join email
      on email.user_id = users.id
  }
  stmt = cli.prepare(sql)
  query_params = []
  results = stmt.execute(*query_params)  
  data = []
  results.each do |r|
    rdata = []
    r.values.each_with_index do |v, c|
      # type = types[c];
      rdata.push(v)
    end
    data.push(rdata)
  end
  data
end

get '/' do
  erb :index
end

get '/listing' do
  erb :listing, :locals => {data: contact_list}
end