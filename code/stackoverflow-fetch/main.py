__author__ = 'Michiel'
import httplib2
import json


def get_data(name):
    url = "https://api.stackexchange.com/2.2/questions?pagesize=100&order=desc&sort=activity&tagged=%s&site=stackoverflow&key=b6gI4yC5CtL7mJMPfK8LOw((" % name
    resp, content = httplib2.Http().request(url)
    print "initial fetch complete"
    json_latest = json.loads(content)
    json_res = json_latest.copy()
    i = 2
    while json_latest.get("has_more", None):
        new_url = url + "&page=" + str(i)
        print "fetching next page: " + str(i)
        resp, content = httplib2.Http().request(new_url)
        json_latest = json.loads(content)
        try:
            remaining = json_latest["quota_remaining"]
        except KeyError as e:
            "quota is no more"
            break

        print "quota_remaining: " + str(json_latest["quota_remaining"])
        json_latest_c = json_latest.copy()
        json_res['items'].append(json_latest_c['items'])
        i += 1

    print "done fetching %d pages" % i

    # print json_res

    filename = 'data_%s.json' % name
    with open(filename, 'w') as outfile:
        json.dump(json_res, outfile)

items = [
    'spring-mvc'
]

for item in items:
    print "----------start fetchting %s----------" % item
    get_data(item)
    print "----------done fetching %s----------" % item