__author__ = 'Michiel'
import httplib2
import json


def get_data(name):
    url = "https://api.stackexchange.com/2.2/questions?pagesize=100&order=desc&sort=activity&tagged=%s&site=stackoverflow" % name
    resp, content = httplib2.Http().request(url)
    print "initial fetch complete"
    json_latest = json.loads(content)
    json_res = json_latest.copy()
    i = 2
    while json_latest["has_more"]:
        new_url = url + "&page=" + str(i)
        print "fetching next page: " + str(i)
        resp, content = httplib2.Http().request(new_url)
        json_latest = json.loads(content)
        print "quota_remaining: " + str(json_latest["quota_remaining"])
        json_res = json_latest.copy()
        json_res.update(json_latest)
        i += 1

    print "done fetching %d pages" % i

    print json_res

    filename = 'data_%s.json' % name
    with open(filename, 'w') as outfile:
        json.dump(json_res, outfile)

items = [
    'playframework-2.0',
    'playframework-2.1',
    'playframework-2.2',
    'playframework-2.3',
    'playframework-json',
    'playframework-1.x']

for item in items:
    print "----------start fetchting %s----------" % item
    get_data(item)
    print "----------done fetching %s----------" % item