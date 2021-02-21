# Task 2, Prioritization

**Question**: You have 10 minutes to test a release of Whatsapp before it hits production, what would you test? Assume you have only one device (iOS or Android), so no need to include cross device testing. It must be doable within 10 minutes manually, so test it yourself!

## Disclaimer

It's been years since I last used Whatsapp app, so I may be missing some features created since then. I'll focus on main functionalities expected for a generic messaging app.

## Answer

* Preconditions: 

1. I'll use a phone with previous version already installed and working, with chats created and populated. That's beacuse breaking app for current users is much worse than not allowing new users to join. If after performing the below steps we still have time we can use it to install app from scratch and do some of that steps.
2. A collegue will stand by me holding a phone with the previous version installed and an open chat with me.

Steps to follow:

1. Install new version with the 'Flight mode' activated (if possible)
2. Activate 'Fligth mode' if not previously activated
3. Open app and check:
    1. App shows "not online" warning
    2. App correctly shows previously created chats
4. Send a text message to an existing chat, containg text and emojis; check it shows the icon for 'pending/not sent' status
5. Send a voice message to an existing chat; check it shows the icon for 'pending/not sent' status
6. Tell collegue to do the same inside the chat open with me
7. Deactivate 'Flight mode' and check:
    1. App shows 'online' status (or does not show offline warning anymore)
    2. Pending/not sent messages are sent
    3. Pending/not received messages are received
8. Send text and voice messages and check they are received
9. Ask collegue to send text and voice messages and check they are received

I guess that, at this point, we are very close to 10 minutes. If still have some time to test:

1. Try to stablish/receive video calls
2. Try to fresh install and recuperate chats for old account
3. Try to fresh install and create new account

At  this point I'm pretty sure you run out of time.

# Task 3, Linux

**Question**: How would you:
- Login (SSH) into a server with IP 45.65.34.23, user root and a private key / identity
file called "MyPrivateKey.key"
- Go to folder "/var/logs"
- Show on console the latest 30 lines of the file "super-app.log"

> ssh -i MyPrivateKey.key root@45.65.34.23
> cd /var/logs
> tail -n 30 super-app.log

